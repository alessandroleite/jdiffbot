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

import diffboat.model.builder.ArticleBuilderImpl;

public final class DiffbotAPI
{
    /**
     * The user's token.
     */
    private final String token_;

    /**
     * Creates an instance of this class using the user's token.
     * 
     * @param token
     *            The token to be used. Might not be <code>null</code>.
     */
    public DiffbotAPI(String token)
    {
        this.token_ = token;
    }

    /**
     * Returns an instance of the {@link ArticleBuilder} to invoke the article services.
     * 
     * @return An instance of the {@link ArticleBuilder} to invoke the article services.
     */
    public ArticleBuilder article()
    {
        return new ArticleBuilderImpl(token_);
    }

    /**
     * Factory method to the {@link DiffbotAPI}.
     * 
     * @param token
     *            The user token. Might not be <code>null</code> or empty.
     * @return An instance of the {@link DiffbotAPI}.
     */
    public static DiffbotAPI newInstance(String token)
    {
        return new DiffbotAPI(token);
    }
}
