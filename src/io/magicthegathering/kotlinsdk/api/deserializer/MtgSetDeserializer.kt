package io.magicthegathering.kotlinsdk.api.deserializer

import com.google.gson.*
import io.magicthegathering.kotlinsdk.api.util.DateTimeFormatterSingleton
import io.magicthegathering.kotlinsdk.model.set.MtgSet
import org.joda.time.DateTime
import java.lang.reflect.Type

class MtgSetDeserializer : JsonDeserializer<MtgSet> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): MtgSet? {
        val gson: Gson = GsonBuilder().create()

        val jsonObject = json?.asJsonObject
        var mtgSet: MtgSet? = null

        if (jsonObject != null && !jsonObject.isJsonNull) {
            val releaseDate: DateTime = DateTime.parse(jsonObject.get("releaseDate").asString, DateTimeFormatterSingleton.instance)

            mtgSet = gson.fromJson<MtgSet>(jsonObject, MtgSet::class.java)
            mtgSet.releaseDate = releaseDate
        }

        return mtgSet
    }
}