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
package diffboat.util.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import diffboat.model.Article;
import diffboat.model.Media;
import diffboat.model.Medias;
import diffboat.type.json.ArticleJsonDeserializer;
import diffboat.type.json.MediaJsonDeserializer;
import diffboat.type.json.MediasJsonDeserializer;

public final class GsonFactory {

	private GsonFactory() {
		throw new UnsupportedOperationException();
	}

	public static <T> T parser(String json, Class<T> type) {
		Gson gson = new GsonBuilder()
		 		.registerTypeAdapter(Article.class, new ArticleJsonDeserializer())
				.registerTypeAdapter(Media.class, new MediaJsonDeserializer())
				.registerTypeAdapter(Medias.class, new MediasJsonDeserializer())
				.create();
		return gson.fromJson(json, type);
	}
}