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

public final class Media implements Serializable {

	/**
	 * Serial code version <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 7744306540133027033L;
	
	private final String type;
	private final String link;
	private final boolean primary;
	

	/**
	 * @param type
	 * @param link
	 */
	public Media(String link, String type, boolean primary) {
		this.link = link;
		this.type = type;
		this.primary = primary;
	}
	
	public Media(String link, String type){
		this(link, type, false);
	}

	/**
	 * @return the type
	 */
	public String type() {
		return type;
	}

	/**
	 * @return the link
	 */
	public String link() {
		return link;
	}
	

	/**
	 * @return the primary
	 */
	public boolean isPrimary() {
		return primary;
	}

	@Override
	public String toString() {
		return "Media - type:" + this.type() + " link:" + this.link();
	}
}