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
package diffboat.type.json;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import diffboat.model.Article;
import diffboat.model.Medias;

import static diffboat.type.json.util.JsonUtil.asString;

public class ArticleJsonDeserializer implements JsonDeserializer<Article>
{

    @Override
    public Article deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        JsonObject jsonObject = (JsonObject) json;

        Article article = new Article(asString(jsonObject.get("title")), 
                asString(jsonObject.get("text")), 
                asString(jsonObject.get("date")),
                asString(jsonObject.get("author")), 
                (Medias) context.deserialize(jsonObject.get("media"), Medias.class),
                asString(jsonObject.get("xpath")), 
                asString(jsonObject.get("icon")), 
                asString(jsonObject.get("url")),
                asString(jsonObject.get("resolved_url")));
        
        article.setHtml(asString(jsonObject.get("html")));
        article.setTags((String[]) context.deserialize(jsonObject.get("tags"), String[].class));
        article.setSummary(asString(jsonObject.get("summary")));

        return article;
    }
}
