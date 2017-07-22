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
                var jsonElement: JsonElement = elementAdapter?.read(`in`) as JsonElement

                if (jsonElement!!.isJsonObject) {
                    val jsonObject: JsonObject = jsonElement.asJsonObject
                    val jsonObjectParentKey = jsonObject.entrySet().first().key

                    if (jsonObjectParentKey == "cards") {
                        jsonElement = jsonObject.getAsJsonArray("cards")
                    } else if (jsonObjectParentKey == "sets") {
                        jsonElement = jsonObject.getAsJsonArray("sets")
                    } else if (jsonObjectParentKey == "types") {
                        jsonElement = jsonObject.getAsJsonArray("types")
                    } else if (jsonObjectParentKey == "supertypes") {
                        jsonElement = jsonObject.getAsJsonArray("supertypes")
                    } else if (jsonObjectParentKey == "subtypes") {
                        jsonElement = jsonObject.getAsJsonArray("subtypes")
                    } else if (jsonObjectParentKey == "card") {
                        jsonElement = jsonObject.get("card")
                    } else if (jsonObjectParentKey == "set") {
                        jsonElement = jsonObject.get("set")
                    } else {
                        jsonElement = jsonObject
                    }
                }

                return delegate!!.fromJsonTree(jsonElement)
            }
        }.nullSafe()
    }
}