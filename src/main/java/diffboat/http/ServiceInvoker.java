/**
 * Copyright (c) 2012 Contributors
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
 *
 * Contributors:
 *          Alessandro Ferreira Leite - the initial implementation.
 */
package diffboat.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import diffboat.util.gson.GsonFactory;

public final class ServiceInvoker
{
    /**
     * The Diffbot API URL.
     */
    private static final String API_SERVER = "http://www.diffbot.com/api/";

    /**
     * The default buffer size to use.
     */
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    /**
     * The parameters of the service.
     */
    private final Map<String, HttpParam> parameters_ = new HashMap<String, ServiceInvoker.HttpParam>();

    /**
     * The URI of the service.
     */
    private final String uri_;

    /**
     * The token API. Might not be <code>null</code>.
     */
    private final String token_;

    public ServiceInvoker(String token, String uri)
    {
        this.uri_ = uri;
        this.token_ = token;
        this.addParam("token", token);
    }

    public HttpParam addParam(HttpParam param)
    {
        return this.parameters_.put(param.getName(), param);
    }

    public HttpParam addParam(String name, String value)
    {
        return this.addParam(new HttpParam(name, value));
    }

    public void addParams(List<HttpParam> params)
    {
        if (params != null)
        {
            for (HttpParam param : params)
            {
                this.addParam(param);
            }
        }
    }

    public HttpParam removeParam(String name)
    {
        return this.parameters_.remove(name);
    }

    public HttpParam removeParam(HttpParam param)
    {
        return this.removeParam(param.getName());
    }

    public HttpParam getParameter(String name)
    {
        return this.parameters_.get(name);
    }

    public Collection<HttpParam> getParameters()
    {
        return Collections.unmodifiableCollection(this.parameters_.values());
    }

    public String getUri()
    {
        return uri_;
    }

    public String getToken()
    {
        return this.token_;
    }

    public final static class HttpParam implements Serializable
    {
        /**
         * Serial code version <code>serialVersionUID</code> for serialization.
         */
        private static final long serialVersionUID = -1877460679133634095L;

        /**
         * The param's name. Might not be <code>null</code>.
         */
        private final String name_;

        /**
         * The param's value.
         */
        private final String value_;

        public HttpParam(String name, String value)
        {

            if (name == null || name.trim().isEmpty())
            {
                throw new IllegalArgumentException("The param name is null or empty!");
            }

            this.name_ = name;
            this.value_ = value;
        }

        public HttpParam(String name)
        {
            this(name, "");
        }

        /**
         * @return the name
         */
        public String getName()
        {
            return name_;
        }

        /**
         * @return the value
         */
        public String getValue()
        {
            return value_;
        }

        @Override
        public boolean equals(Object obj)
        {
            if (obj == this)
            {
                return true;
            }

            if (!(obj instanceof HttpParam))
            {
                return false;
            }

            return this.name_.equalsIgnoreCase(((HttpParam) obj).name_);
        }

        @Override
        public int hashCode()
        {
            return this.getName().hashCode() * 17;
        };

        @Override
        public String toString()
        {
            return String.format("Name: %s, value: %s", this.getName(), this.getValue());
        }
    }

    /**
     * Returns the URL of the service with the HTTP parameters defined to the service.
     * 
     * @return The URL of the service with the HTTP parameters defined to the service.
     */
    private String buildURL()
    {
        StringBuilder url = new StringBuilder();

        for (HttpParam param : this.getParameters())
        {
            url.append("&").append(param.getName());

            if (param.getValue() != null && !param.getValue().isEmpty())
            {
                url.append("=").append(param.getValue());
            }
        }
        return String.format("%s%s?%s", API_SERVER, this.getUri(), url.toString());
    }

    /**
     * Copy bytes from a {@link Reader} to an {@link Writer}
     * <p>
     * This method buffers the input internally, so there is no need to use a <code>BufferedInputStream</code>.
     * 
     * @param input
     *            the {@link Reader} to read from
     * @param output
     *            the {@link Writer} output to write to
     * @return the number of bytes copied
     * @throws NullPointerException
     *             if the input or output is null
     * @throws IOException
     *             if an I/O error occurs
     */
    protected long copy(Reader input, Writer output) throws IOException
    {
        char[] buffer = new char[DEFAULT_BUFFER_SIZE];
        long count = 0;
        int n;

        while ((n = input.read(buffer)) != -1)
        {
            output.write(buffer, 0, n);
            count += n;
        }

        return count;
    }

    /**
     * Get the contents of an <code>InputStream</code> as a String using the default character encoding of the platform.
     * 
     * @param input
     *            the <code>InputStream</code> to read from
     * @return the requested String
     * @throws NullPointerException
     *             if the input is null
     * @throws IOException
     *             if an I/O error occurs
     */
    protected String asString(InputStream input) throws IOException
    {
        StringWriter sw = new StringWriter();
        copy(new InputStreamReader(input), sw);
        return sw.toString();
    }

    public Response execute() throws IOException
    {
        StringBuilder output = new StringBuilder();

        Response response = this.execute(Response.class, output);
        response.setJson(output.toString());

        return response;
    }

    public <T> T execute(Class<T> jsonType) throws IOException
    {
        return this.execute(jsonType, new StringBuilder());
    }

    protected <T> T execute(Class<T> jsonType, StringBuilder buffer) throws IOException
    {
        URL url = new URL(buildURL());
        buffer.append(asString(url.openStream()));
        return GsonFactory.parser(buffer.toString(), jsonType);
    }

    /**
     * Encode a given {@link String} using the UTF-8 encoding.
     * 
     * @param value
     *            URL to be translated.
     * @return The translated {@link String}.
     * @throws RuntimeException If the JVM does not support the UTF-8 encoding.
     */
    public static String encode(String value)
    {
        try
        {
            return URLEncoder.encode(value, "UTF8");
        }
        catch (UnsupportedEncodingException exception)
        {
            throw new RuntimeException("The JVM does not support UTF-8 encoding!", exception);
        }
    }
}
