package io.magicthegathering.kotlinsdk.api.deserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import io.magicthegathering.kotlinsdk.api.util.DateTimeFormatterSingleton
import io.magicthegathering.kotlinsdk.model.card.MtgCardRuling
import org.joda.time.DateTime
import java.lang.reflect.Type

class MtgCardRulingDeserializer : JsonDeserializer<MtgCardRuling> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): MtgCardRuling? {
        val jsonObject = json?.asJsonObject
        var ruling: MtgCardRuling? = null

        if (jsonObject != null && !jsonObject.isJsonNull) {
            val date: DateTime = DateTime.parse(jsonObject.get("date").asString, DateTimeFormatterSingleton.instance)
            ruling = MtgCardRuling(date, jsonObject.get("text").asString)
        }

        return ruling
    }
}