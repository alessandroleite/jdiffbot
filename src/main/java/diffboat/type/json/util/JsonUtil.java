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
package diffboat.type.json.util;

import com.google.gson.JsonElement;

public final class JsonUtil
{
    /**
     * Private constructor to avoid instances of this class.
     */
    private JsonUtil()
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns a {@link String} value of a given {@link JsonElement}.
     * 
     * @param jsonElement The {@link JsonElement} to get its string value.
     * @return a {@link String} value of the given {@link JsonElement}.
     */
    public static String asString(JsonElement jsonElement)
    {
        return jsonElement != null ? jsonElement.getAsString() : null;
    }

    /**
     * Returns a {@link Boolean} value of a given {@link JsonElement}.
     * 
     * @param jsonElement The {@link JsonElement} to get its boolean value.
     * @return a {@link Boolean} value of the given {@link JsonElement}.
     */
    public static boolean asBoolean(JsonElement jsonElement)
    {
        return jsonElement == null ? false : jsonElement.getAsBoolean();
    }
}
