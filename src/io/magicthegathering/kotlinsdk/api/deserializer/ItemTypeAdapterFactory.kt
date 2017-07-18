package io.magicthegathering.kotlinsdk.api.deserializer

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.io.IOException

class ItemTypeAdapterFactory : TypeAdapterFactory {

    override fun <T : Any?> create(gson: Gson?, type: TypeToken<T>?): TypeAdapter<T> {
        val delegate = gson?.getDelegateAdapter(this, type)
        val elementAdapter = gson?.getAdapter(JsonElement::class.java)

        return object : TypeAdapter<T>() {
            @Throws(IOException::class)
            override fun write(out: JsonWriter, value: T) {
                delegate!!.write(out, value)
            }

            @Throws(IOException::class)
            override fun read(`in`: JsonReader): T {
                val jsonObject: JsonObject = elementAdapter?.read(`in`)!!.asJsonObject

                val jsonElement: JsonElement

                if (jsonObject.has("cards")) {
                    jsonElement = jsonObject.getAsJsonArray("cards")
                } else if (jsonObject.has("sets")) {
                    jsonElement = jsonObject.getAsJsonArray("sets")
                } else if (jsonObject.has("card")) {
                    jsonElement = jsonObject.get("card")
                } else {
                    jsonElement = jsonObject
                }

                return delegate!!.fromJsonTree(jsonElement)
            }
        }.nullSafe()
    }
}