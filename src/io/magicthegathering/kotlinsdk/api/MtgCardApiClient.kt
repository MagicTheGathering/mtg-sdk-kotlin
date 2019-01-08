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
         * @param pageSize The page size.
         * @param page The next page.
         * @return Returns a list containing all of the Magic: The Gathering cards.
         */
        fun getAllCards(pageSize: Int = 10, page: Int = 0): Response<List<MtgCard>> {
            return instance.getAllCards(pageSize, page).execute()
        }

        /**
         * Get all Magic: The Gathering cards by set code.
         *
         * @param setCode The set code.
         * @param pageSize The page size.
         * @param page The next page.
         * @return Returns an Observable that emits a list containing all of the Magic: The Gathering cards.
         */
        fun getAllCardsBySetCodeObservable(setCode: String, pageSize: Int = 10, page: Int = 0): Observable<List<MtgCard>> {
            return instance.getAllCardsBySetCodeObservable(setCode, pageSize, page)
        }

        /**
         * Get all Magic: The Gathering cards by set code.
         *
         * @param setCode The set code.
         * @param pageSize The page size.
         * @param page The next page.
         * @return Returns a list containing all of the Magic: The Gathering cards.
         */
        fun getAllCardsBySetCode(setCode: String, pageSize: Int = 10, page: Int = 0): Response<List<MtgCard>> {
            return instance.getAllCardsBySetCode(setCode, pageSize, page).execute()
        }

        /**
         * Get a specific Magic: The Gathering card.
         *
         * @param multiverseId The official Magic: The Gathering card id.
         * @return Returns an Observable that emits a specific Magic: The Gathering card.
         */
        fun getCardObservable(multiverseId: Int): Observable<MtgCard> {
            return instance.getCardObservable(multiverseId)
        }

        /**
         * Get a specific Magic: The Gathering card.
         *
         * @param multiverseId The official Magic: The Gathering card id.
         * @return Returns a specific Magic: The Gathering card.
         */
        fun getCard(multiverseId: Int): Response<MtgCard> {
            return instance.getCard(multiverseId).execute()
        }

        /**
         * Get Magic: The Gathering cards by partial name.
         *
         * @param name The partial name to search.
         * @param pageSize The page size.
         * @param page The next page.
         * @return Returns an Observable that emits a list containing the search result.
         */
        fun getCardsByPartialNameObservable(name: String, pageSize: Int, page: Int): Observable<List<MtgCard>> {
            return instance.getCardsByNameObservable(name, pageSize, page)
        }

        /**
         * Get Magic: The Gathering cards by partial name.
         *
         * @param name The partial name to search.
         * @param pageSize The page size.
         * @param page The next page.
         * @return Returns a list containing the search result.
         */
        fun getCardsByPartialName(name: String, pageSize: Int, page: Int): Response<List<MtgCard>> {
            return instance.getCardsByName(name, pageSize, page).execute()
        }

        /**
         * Get Magic: The Gathering cards by exact name.
         *
         * @param name The exact name to search.
         * @param pageSize The page size.
         * @param page The next page.
         * @return Returns an Observable that emits a list containing the search result.
         */
        fun getCardsByExactNameObservable(name: String, pageSize: Int, page: Int): Observable<List<MtgCard>> {
            return instance.getCardsByNameObservable("\"$name\"", pageSize, page)
        }

        /**
         * Get Magic: The Gathering cards by exact name.
         *
         * @param name The exact name to search.
         * @param pageSize The page size.
         * @param page The next page.
         * @return Returns a list containing the search result.
         */
        fun getCardsByExactName(name: String, pageSize: Int, page: Int): Response<List<MtgCard>> {
            return instance.getCardsByName("\"$name\"", pageSize, page).execute()
        }
    }

    private interface MtgCardApi {

        @GET("cards")
        fun getAllCardsObservable(@Query("pageSize") pageSize: Int, @Query("page") page: Int): Observable<List<MtgCard>>

        @GET("cards")
        fun getAllCards(@Query("pageSize") pageSize: Int, @Query("page") page: Int): Call<List<MtgCard>>

        @GET("cards")
        fun getAllCardsBySetCodeObservable(@Query("set") setCode: String, @Query("pageSize") pageSize: Int, @Query("page") page: Int): Observable<List<MtgCard>>

        @GET("cards")
        fun getAllCardsBySetCode(@Query("set") setCode: String, @Query("pageSize") pageSize: Int, @Query("page") page: Int): Call<List<MtgCard>>

        @GET("cards/{multiverseId}")
        fun getCardObservable(@Path("multiverseId") multiverseId: Int): Observable<MtgCard>

        @GET("cards/{multiverseId}")
        fun getCard(@Path("multiverseId") multiverseId: Int): Call<MtgCard>

        @GET("cards")
        fun getCardsByNameObservable(@Query("name") name: String, @Query("pageSize") pageSize: Int, @Query("page") page: Int): Observable<List<MtgCard>>

        @GET("cards")
        fun getCardsByName(@Query("name") name: String, @Query("pageSize") pageSize: Int, @Query("page") page: Int): Call<List<MtgCard>>
    }
}