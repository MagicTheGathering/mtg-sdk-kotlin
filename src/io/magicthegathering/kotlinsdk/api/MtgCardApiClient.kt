package io.magicthegathering.kotlinsdk.api

import io.magicthegathering.kotlinsdk.model.card.MtgCard
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

class MtgCardApiClient private constructor() {

    private object Holder {
        val INSTANCE: MtgCardApi = ApiClientBuilder.instance.create(MtgCardApi::class.java)
    }

    companion object {
        private val instance: MtgCardApi by lazy { Holder.INSTANCE }

        fun getAllCards(pageSize: Int = 10, page: Int = 0): Observable<List<MtgCard>> {
            return instance.getAllCards(pageSize, page)
        }

        fun getCard(multiverseId: Int): Observable<MtgCard> {
            return instance.getCard(multiverseId)
        }

        fun getCardsByPartialName(name: String, pageSize: Int, page: Int): Observable<List<MtgCard>> {
            return instance.getCardsByName(name, pageSize, page)
        }

        fun getCardsByExactName(name: String, pageSize: Int, page: Int): Observable<List<MtgCard>> {
            return instance.getCardsByName("\"$name\"", pageSize, page)
        }

        fun generateBoosterPackBySetCode(setCode: String): Observable<List<MtgCard>> {
            return instance.generateBoosterPackBySetCode(setCode)
        }

        fun getCardsByPartialNameWithNonEnglishLanguage(language: String, name: String, pageSize: Int, page: Int): Observable<List<MtgCard>> {
            return instance.getCardsByNameWithNonEnglishLanguage(language, name, pageSize, page)
        }

        fun getCardsByExactNameWithNonEnglishLanguage(language: String, name: String, pageSize: Int, page: Int): Observable<List<MtgCard>> {
            return instance.getCardsByNameWithNonEnglishLanguage(language, "\"$name\"", pageSize, page)
        }
    }

    private interface MtgCardApi {

        @GET("cards")
        fun getAllCards(@Query("pageSize") pageSize: Int, @Query("page") page: Int): Observable<List<MtgCard>>

        @GET("cards/{multiverseId}")
        fun getCard(@Path("multiverseId") multiverseId: Int): Observable<MtgCard>

        @GET("cards")
        fun getCardsByName(@Query("name") name: String, @Query("pageSize") pageSize: Int, @Query("page") page: Int): Observable<List<MtgCard>>

        @GET("sets/{setCode}/booster")
        fun generateBoosterPackBySetCode(@Path("setCode") setCode: String): Observable<List<MtgCard>>

        @GET("cards")
        fun getCardsByNameWithNonEnglishLanguage(@Query("language") language: String, @Query("name") name: String, @Query("pageSize") pageSize: Int, @Query("page") page: Int): Observable<List<MtgCard>>
    }
}