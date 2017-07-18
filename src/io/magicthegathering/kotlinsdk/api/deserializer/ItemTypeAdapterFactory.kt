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
                val jsonObjectParentKey = jsonObject.entrySet().first().key

                val jsonElement: JsonElement

                if (jsonObjectParentKey == "cards") {
                    jsonElement = jsonObject.getAsJsonArray("cards")
                } else if (jsonObjectParentKey == "sets") {
                    jsonElement = jsonObject.getAsJsonArray("sets")
                } else if (jsonObjectParentKey == "card") {
                    jsonElement = jsonObject.get("card")
                } else if (jsonObjectParentKey == "set") {
                    jsonElement = jsonObject.get("set")
                } else {
                    jsonElement = jsonObject
                }

                return delegate!!.fromJsonTree(jsonElement)
            }
        }.nullSafe()
    }
}