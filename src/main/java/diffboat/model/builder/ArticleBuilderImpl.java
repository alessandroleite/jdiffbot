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
package diffboat.model.builder;

import java.io.IOException;

import diffboat.api.ArticleBuilder;
import diffboat.http.ServiceInvoker;
import diffboat.model.Article;

public class ArticleBuilderImpl implements ArticleBuilder {

	/**
	 * Default value for <code>null</code> parameter value.
	 */
	private static final String NULL_PARAM_VALUE = "";

	/**
	 * The article URI.
	 */
	private final String uri = "article";

	/**
	 * The {@link ServiceInvoker} instance of the invoke the article service.
	 */
	private final ServiceInvoker request;

	public ArticleBuilderImpl(String token) {
		request = new ServiceInvoker(token, uri);
	}

	@Override
	public ArticleBuilder extractFrom(String url) {
		request.addParam("url", ServiceInvoker.encode(url));
		return this;
	}

	@Override
	public ArticleBuilder asHtml() {
		request.addParam("url", NULL_PARAM_VALUE);
		return this;
	}

	@Override
	public ArticleBuilder dontStripAds() {
		request.addParam("dontStripAds", NULL_PARAM_VALUE);
		return this;
	}

	@Override
	public ArticleBuilder withTags() {
		request.addParam("tags", NULL_PARAM_VALUE);
		return this;
	}

	@Override
	public ArticleBuilder withComments() {
		request.addParam("comments", NULL_PARAM_VALUE);
		return this;
	}

	@Override
	public ArticleBuilder withSummary() {
		request.addParam("summary", NULL_PARAM_VALUE);
		return this;
	}

	@Override
	public ArticleBuilder withTimeout(long timeout) {
		request.addParam("timeout", String.valueOf(timeout));
		return this;
	}

	@Override
	public Article analyze() throws IOException {
		return request.execute(Article.class);
	}
}