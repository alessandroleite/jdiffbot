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
package diffbot.test;


import java.io.IOException;

import diffboat.api.DiffbotAPI;
import diffboat.model.Article;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

public class ArticleAPITest
{
    /**
     * The user's token to be used during the tests.
     */
    private String token_;

    /**
     * Configure the token to be used.
     */
    @Before
    public void setUp()
    {
        token_ = System.getProperty("token");

        if (token_ == null || token_.isEmpty())
        {
            token_ = System.getenv("token");
        }
    }

    /**
     * @throws IOException If an I/O error occurs.
     */
    @Test
    public void must_extract_article_text() throws IOException
    {
        String uri = "http://www.bbc.co.uk/news/world-us-canada-20750666";
        if (token_ != null)
        {
            Article article = DiffbotAPI.newInstance(this.token_)
                    .article()
                    .extractFrom(uri)
                    .withTags()
                    .withComments()
                    .withSummary()
                    .analyze();

            assertNotNull(article);
            assertNotNull(article.getText());
        }
    }
}
