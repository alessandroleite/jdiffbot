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
package diffboat.model.builder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import diffboat.api.ArticleBuilder;
import diffboat.http.Request;
import diffboat.http.Request.Type;
import diffboat.model.Article;

public class ArticleBuilderImpl implements ArticleBuilder {
	
	private final String uri = "article";
	private final String token;
	private List<Type> parameters = new ArrayList<Request.Type>();
	
	public ArticleBuilderImpl(String token) {
		this.token = token;
		parameters.add(new Type("token", token));
	}

	@Override
	public ArticleBuilder extractFrom(String url) {
		parameters.add(new Type("url", url));
		return this;
	}

	@Override
	public ArticleBuilder asHtml() {
		parameters.add(new Type("html",""));
		return this;
	}

	@Override
	public ArticleBuilder dontStripAds() {
		parameters.add(new Type("dontStripAds", ""));
		return this;
	}

	@Override
	public ArticleBuilder withTags() {
		parameters.add(new Type("tags", ""));
		return this;
	}

	@Override
	public ArticleBuilder withComments() {
		parameters.add(new Type("comments", ""));
		return this;
	}

	@Override
	public ArticleBuilder withSummary() {
		parameters.add(new Type("summary", ""));
		return this;
	}

	@Override
	public Article analyze() throws IOException {
		Request request = new Request(this.token, this.uri);
		request.addParams(this.parameters);
		return request.execute(Article.class);
	}
}