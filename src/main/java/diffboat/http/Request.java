/**
 * Copyright (c) 2012 Alessandro Ferreira Leite, http://www.alessandro.cc/
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package diffboat.http;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;

import diffboat.http.session.Session;
import diffboat.http.session.WebSession;
import diffboat.util.gson.GsonFactory;

public class Request implements Serializable{

	/**
	 * Serial code version <code>serialVersionUID</code> 
	 */
	private static final long serialVersionUID = -4022710058912757519L;
	
	private final Map<String, Type> parameters = new HashMap<String, Request.Type>();
	private final List<Type> headers = new ArrayList<Request.Type>();

	private final String uri;
	private final String token;
	private Session session;

	public Request(String token, String uri) {
		this.uri = uri;
		this.token = token;
	}
	
	Object mutex = new Integer(-1);
	
	public Session getSession(boolean created) {
		synchronized (mutex) {
			if (created && session == null) {
				session = new WebSession(this.getToken());
			}
		}
		return this.session;
	}

	public Session getSession() {
		return this.getSession(true);
	}

	public Type addParam(Type param) {
		return this.parameters.put(param.getName(), param);
	}

	public Type addParam(String name, String value) {
		return this.addParam(new Type(name, value));
	}

	public Type addParam(ParamType type, String value) {
		return this.addParam(type.name().toLowerCase(), value);
	}

	public void addParams(List<Type> params) {
		if (params != null) {
			for (Type param : params) {
				this.addParam(param);
			}
		}
	}

	public Type removeParam(String name) {
		return this.parameters.remove(name);
	}

	public Type removeParam(Type param) {
		return this.removeParam(param.getName());
	}

	public Type getParameter(String name) {
		return this.parameters.get(name);
	}

	public Collection<Type> getParameters() {
		return Collections.unmodifiableCollection(this.parameters.values());
	}

	public boolean addHeader(String name, String value) {
		return this.addHeader(new Type(name, value));
	}

	public boolean addHeader(Type type) {
		return this.headers.add(checkNotNull(type));
	}

	public List<Type> getHeaders() {
		return Collections.unmodifiableList(headers);
	}

	public String getUri() {
		return uri;
	}

	public String getToken() {
		return this.token;
	}

	public static enum ParamType {
		REDIRECT_URI;
	}
	

	public final static class Type implements Serializable {

		/**
		 * Serial code version <code>serialVersionUID</code>
		 */
		private static final long serialVersionUID = -2322758321566274906L;

		private final String name;
		private final String value;

		public Type(String name, String value) {
			this.name = checkNotNull(name);
			this.value = value;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this)
				return true;

			if (!(obj instanceof Type))
				return false;

			return this.name.equalsIgnoreCase(((Type) obj).name);
		}

		@Override
		public int hashCode() {
			return this.getName().hashCode() * 17;
		};

		@Override
		public String toString() {
			return String.format("Name: %s, value: %s", this.getName(),
					this.getValue());
		}
	}
	
	
	private URI buildURI() {
		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost(this.getSession().getAPIServer())
				.setPath(this.getUri());

		for (Type param : this.getParameters()) {
			builder.setParameter(param.getName(), param.getValue());
		}

		try {
			return builder.build();
		} catch (URISyntaxException exception) {
			throw new RuntimeException(exception);
		}
	}

	protected HttpUriRequest createHttpRequest() throws IOException {
		HttpGet httpget = new HttpGet(buildURI());
		addHeaders(httpget);

		return httpget;
	}


	protected void addHeaders(HttpUriRequest post) {
		for (Type type : this.getHeaders()) {
			post.addHeader(type.getName(), type.getValue());
		}
	}

	protected void asString(HttpResponse response, StringBuilder buffer) throws IOException {
		HttpEntity ent = response.getEntity();
		if (ent != null) {
			buffer.append(IOUtils.toString(ent.getContent()));
		}
	}

	public Response execute() throws IOException {
		StringBuilder output = new StringBuilder();

		Response response = this.execute(Response.class, output);
		response.setJson(output.toString());

		return response;
	}

	public <T> T execute(Class<T> jsonType) throws IOException {
		return this.execute(jsonType, new StringBuilder());
	}

	protected <T> T execute(Class<T> jsonType, StringBuilder buffer) throws IOException {

		HttpUriRequest httpMethod = this.createHttpRequest();
		HttpResponse response = this.getSession().getHttpClient().execute(httpMethod);
		asString(response, buffer);
		
		return GsonFactory.parser(buffer.toString(), jsonType);
	}
}