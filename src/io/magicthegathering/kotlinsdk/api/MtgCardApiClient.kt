package io.magicthegathering.kotlinsdk.api

import io.magicthegathering.kotlinsdk.model.card.MtgCard
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class MtgCardApiClient private constructor() {

    private object Holder {
        val INSTANCE: MtgCardApi = ApiClientBuilder.instance.create(MtgCardApi::class.java)
    }

    companion object {
        private val instance: MtgCardApi by lazy { Holder.INSTANCE }

        /**
         * Get all Magic: The Gathering cards.
         *
         * @see <a href="https://docs.magicthegathering.io/#get-all-cards">Get all cards - Endpoint</a>
         * @param pageSize The page size.
         * @param page The next page.
         * @return Returns an Observable that emits a list containing all of the Magic: The Gathering cards.
         */
        fun getAllCardsObservable(pageSize: Int = 10, page: Int = 0): Observable<List<MtgCard>> {
            return instance.getAllCardsObservable(pageSize, page)
        }

        /**
         * Get all Magic: The Gathering cards.
         *
         * @see <a href="https://docs.magicthegathering.io/#get-all-cards">Get all cards - Endpoint</a>
         * @param pageSize The page size.
         * @param page The next page.
         * @return Returns a list containing all of the Magic: The Gathering cards.
         */
        fun getAllCards(pageSize: Int = 10, page: Int = 0): Response<List<MtgCard>> {
            return instance.getAllCards(pageSize, page).execute()
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

        fun getCardsByPartialNameWithNonEnglishLanguage(language: String, name: String, pageSize: Int, page: Int): Observable<List<MtgCard>> {
            return instance.getCardsByNameWithNonEnglishLanguage(language, name, pageSize, page)
        }

        fun getCardsByExactNameWithNonEnglishLanguage(language: String, name: String, pageSize: Int, page: Int): Observable<List<MtgCard>> {
            return instance.getCardsByNameWithNonEnglishLanguage(language, "\"$name\"", pageSize, page)
        }
    }

    private interface MtgCardApi {

        @GET("cards")
        fun getAllCardsObservable(@Query("pageSize") pageSize: Int, @Query("page") page: Int): Observable<List<MtgCard>>

        @GET("cards")
        fun getAllCards(@Query("pageSize") pageSize: Int, @Query("page") page: Int): Call<List<MtgCard>>

        @GET("cards/{multiverseId}")
        fun getCard(@Path("multiverseId") multiverseId: Int): Observable<MtgCard>

        @GET("cards")
        fun getCardsByName(@Query("name") name: String, @Query("pageSize") pageSize: Int, @Query("page") page: Int): Observable<List<MtgCard>>

        @GET("cards")
        fun getCardsByNameWithNonEnglishLanguage(@Query("language") language: String, @Query("name") name: String, @Query("pageSize") pageSize: Int, @Query("page") page: Int): Observable<List<MtgCard>>
    }
}