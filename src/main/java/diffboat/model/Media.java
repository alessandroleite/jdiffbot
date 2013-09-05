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
package diffboat.model;

import java.io.Serializable;

public final class Media implements Serializable
{

    /**
     * Serial code version <code>serialVersionUID</code> for serialization.
     */
    private static final long serialVersionUID = 7744306540133027033L;

    /**
     * The media type (audio, video, etc.).
     */
    private final String type_;
    
    /**
     * The link to the media. 
     */
    private final String link_;
    
    /**
     * A flag that indicates if it's primary.
     */
    private final boolean primary_;

    /**
     * 
     * @param link The link to the media.
     * @param type The type of the media.
     * @param primary a flag indicating if the media is primary or not.
     */
    public Media(String link, String type, boolean primary)
    {
        this.link_ = link;
        this.type_ = type;
        this.primary_ = primary;
    }

    /**
     * Creates an {@link Media} instance with the link and type.
     * 
     * @param link The link to the media.
     * @param type The type of the media.
     */
    public Media(String link, String type)
    {
        this(link, type, false);
    }

    /**
     * @return the type
     */
    public String type()
    {
        return type_;
    }

    /**
     * @return the link
     */
    public String link()
    {
        return link_;
    }

    /**
     * @return the primary
     */
    public boolean isPrimary()
    {
        return primary_;
    }

    @Override
    public String toString()
    {
        return "Media - type:" + this.type() + " link:" + this.link();
    }
}
