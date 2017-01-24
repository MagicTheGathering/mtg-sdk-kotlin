package io.magicthegathering.kotlinsdk.api.deserializer

import com.google.gson.*
import io.magicthegathering.kotlinsdk.model.card.MtgCard
import io.magicthegathering.kotlinsdk.model.card.MtgCardRuling
import java.lang.reflect.Type

class MtgCardDeserializer : JsonDeserializer<MtgCard> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): MtgCard? {
        val gson: Gson = GsonBuilder()
                .registerTypeAdapter(MtgCardRuling::class.java, MtgCardRulingDeserializer())
                .create()

        val jsonObject = json?.asJsonObject
        var card: MtgCard? = null

        if (jsonObject != null && !jsonObject.isJsonNull) {
            card = gson.fromJson<MtgCard>(jsonObject, MtgCard::class.java)
        }

        return card
    }
}