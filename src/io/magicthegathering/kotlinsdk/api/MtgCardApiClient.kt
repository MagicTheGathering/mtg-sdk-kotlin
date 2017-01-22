package io.magicthegathering.kotlinsdk.api

import io.magicthegathering.kotlinsdk.model.card.MtgCard
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

class MtgCardApiClient private constructor() {

    private object Holder {
        val INSTANCE: MtgCardApi = ApiClientBuilder.instance.create(MtgCardApi::class.java)
    }

    companion object {
        val instance: MtgCardApi by lazy { Holder.INSTANCE }
    }

    interface MtgCardApi {

        @GET("cards/{multiverseId}")
        fun getCard(@Path("multiverseId") multiverseId: Int): Observable<MtgCard>
    }
}