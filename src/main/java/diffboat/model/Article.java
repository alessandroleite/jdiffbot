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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public final class Article implements Serializable
{
    /**
     * Serial code version <code>serialVersionUID</code> for serialization.
     */
    private static final long serialVersionUID = 7531133216091403402L;

    /** Plain-text of the extracted article. */
    private String text_;

    /** Title of extracted article. */
    private final String title_;

    /** Article date (if detected). */
    private final String date_;

    /** Article author (if detected). */
    private final String author_;

    /** Media items (images or videos), if detected and extracted. */
    private final Medias media_;

    /** The submitted URL. */
    private final String url_;

    /**
     * Returned if the resolving URL is different from the submitted URL (e.g., link shortening services).
     */
    private final String resolvedUrl_;

    /** XPath expression identifying the node containing the article contents. */
    private final String xpath_;

    /** Page favicon. */
    private final String icon_;

    /**
     * HTML of the extracted article (returned in place of text if the html parameter is used).
     */
    private String html_;

    /** Array of tags (returned if tags parameter is used). */
    private String[] tags_;

    /** Summary text (returned if summary parameter is used). */
    private String summary_;

    /**
     * 
     * @param title
     *            The article's title.
     * @param text
     *            The article's text.
     * @param date
     *            The article's date.
     * @param author
     *            The article's author, if available.
     * @param media
     *            The article's medias.
     * @param xpath
     *            The article's xpath.
     * @param icon
     *            The article's icons.
     * @param submittedURL
     *            The article's URL.
     * @param resolvedUrl
     *            The resolved url.
     */
    public Article(String title, String text, String date, String author, Medias media, String xpath, String icon, String submittedURL,
            String resolvedUrl)
    {

        this.title_ = title;
        this.text_ = text;
        this.date_ = date;
        this.author_ = author;
        this.media_ = media;
        this.xpath_ = xpath;
        this.icon_ = icon;
        this.url_ = submittedURL;
        this.resolvedUrl_ = resolvedUrl;
    }

    @Override
    public boolean equals(Object obj)
    {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode()
    {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * @return the text
     */
    public String getText()
    {
        return text_;
    }

    /**
     * @param text
     *            the text to set
     */
    public void setText(String text)
    {
        this.text_ = text;
    }

    /**
     * @return the title
     */
    public String getTitle()
    {
        return title_;
    }

    /**
     * @return the date
     */
    public String getDate()
    {
        return date_;
    }

    /**
     * @return the author
     */
    public String getAuthor()
    {
        return author_;
    }

    /**
     * @return the medias
     */
    public Medias getMedia()
    {
        return media_;
    }

    /**
     * @return the url
     */
    public String getUrl()
    {
        return url_;
    }

    /**
     * @return the resolvedUrl
     */
    public String getResolvedUrl()
    {
        return resolvedUrl_;
    }

    /**
     * @return the xpath
     */
    public String getXpath()
    {
        return xpath_;
    }

    /**
     * @return the icon
     */
    public String getIcon()
    {
        return icon_;
    }

    /**
     * @return the html
     */
    public String getHtml()
    {
        return html_;
    }

    /**
     * @param html
     *            the html to set
     */
    public void setHtml(String html)
    {
        this.html_ = html;
    }

    /**
     * @return the tags
     */
    public String[] getTags()
    {
        return tags_;
    }

    /**
     * @param tags
     *            the tags to set
     */
    public void setTags(String[] tags)
    {
        this.tags_ = tags;
    }

    /**
     * @return the summary
     */
    public String getSummary()
    {
        return summary_;
    }

    /**
     * @param summary
     *            the summary to set
     */
    public void setSummary(String summary)
    {
        this.summary_ = summary;
    }
}
