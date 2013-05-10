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
package diffboat.api;

import java.io.IOException;

import diffboat.model.Article;

public interface ArticleBuilder 
{

	/**
	 * Extract an article of a given URL.
	 * 
	 * @param url
	 *            Encoded URL to extract the {@link Article} from.
	 * @return The same instance but with the URL defined to extract the article. 
	 */
	ArticleBuilder extractFrom(String url);

	/**
	 * Return html instead of plain text.
	 * 
	 * @return The same instance with the result defined as HTML.
	 */
	ArticleBuilder asHtml();

	/**
	 * Doesn't strip any inline ads.
	 * 
	 * @return The same instance but with option to does not strip any inline ads.
	 */
	ArticleBuilder dontStripAds();

	/**
	 * Generate tags for the extracted story.
	 * 
	 * @return The same instance configured to generate the tags of the article.
	 */
	ArticleBuilder withTags();

	/**
	 * Find the comments and identify count, link, etc.
	 * 
	 * @return The same instance configured  to find comments, links, etc.
	 */
	ArticleBuilder withComments();

	/**
	 * Returns a summary text of the article.
	 * 
	 * @return The same instance configured to return a summary of the article.
	 */
	ArticleBuilder withSummary();
	
	/**
	 * Overrides the default API timeout of 5000ms.
	 *  
	 * @param timeout The timeout in milliseconds.
	 * @return The same instance configured the timeout.
	 */
	ArticleBuilder withTimeout(long timeout);

	/**
	 * Returns the {@link Article} according with the options defined. The default option
	 * is plain text output without comments but with a summary.
	 * 
	 * @return the {@link Article} with the options defined. The default option
	 *         is plain text output without comments but with a summary.
	 */
	Article analyze() throws IOException;
}
