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
package diffboat.api;

import java.io.IOException;

import diffboat.model.Article;

public interface ArticleBuilder {

	/**
	 * Extract an article of a given URL.
	 * 
	 * @param url
	 *            Encoded URL to extract the {@link Article} from.
	 * @return An instance of this builder
	 */
	ArticleBuilder extractFrom(String url);

	/**
	 * Return html instead of plain text.
	 * 
	 * @return
	 */
	ArticleBuilder asHtml();

	/**
	 * Don't strip any inline ads
	 * 
	 * @return
	 */
	ArticleBuilder dontStripAds();

	/**
	 * Generate tags for the extracted story.
	 * 
	 * @return
	 */
	ArticleBuilder withTags();

	/**
	 * Find the comments and identify count, link, etc.
	 * 
	 * @return
	 */
	ArticleBuilder withComments();

	/**
	 * Returns a summary text
	 * 
	 * @return
	 */
	ArticleBuilder withSummary();

	/**
	 * Returns the {@link Article} with the options defined. The default option
	 * is plain text output without comments but with a summary.
	 * 
	 * @return the {@link Article} with the options defined. The default option
	 *         is plain text output without comments but with a summ
	 */
	Article analyze() throws IOException;
}
