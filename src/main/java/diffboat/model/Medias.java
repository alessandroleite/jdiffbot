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

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Medias implements Iterable<Media>
{
    /**
     * The delegate {@link List}.
     */
    private List<Media> medias_ = new CopyOnWriteArrayList<Media>();

    /**
     * Returns an unmodifiable {@link Collection} with the available medias.
     * @return An unmodifiable {@link Collection} with the available medias.
     */
    public Collection<Media> get()
    {
        return Collections.unmodifiableList(medias_);
    }

    @Override
    public Iterator<Media> iterator()
    {
        return this.get().iterator();
    }

    /**
     * Adds a new {@link Media} only if it's not <code>null</code>.
     * 
     * @param media The media do be added. <code>null</code> values are not accepted.
     */
    public void add(Media media)
    {
        if (media != null)
        {
            this.medias_.add(media);
        }
    }

    @Override
    public String toString()
    {
        return medias_.toString();
    }
}
