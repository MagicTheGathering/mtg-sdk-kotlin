package io.magicthegathering.kotlinsdk.api

import io.magicthegathering.kotlinsdk.model.card.MtgCard
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

class MtgSetApiClient {

    private object Holder {
        val INSTANCE: MtgSetApi = ApiClientBuilder.instance.create(MtgSetApi::class.java)
    }

    companion object {
        private val instance: MtgSetApi by lazy { MtgSetApiClient.Holder.INSTANCE }

        fun generateBoosterPackBySetCode(setCode: String): Observable<List<MtgCard>> {
            return instance.generateBoosterPackBySetCode(setCode)
        }
    }

    private interface MtgSetApi {

        @GET("sets/{setCode}/booster")
        fun generateBoosterPackBySetCode(@Path("setCode") setCode: String): Observable<List<MtgCard>>
    }
}