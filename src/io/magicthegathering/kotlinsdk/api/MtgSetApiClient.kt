package io.magicthegathering.kotlinsdk.api

import io.magicthegathering.kotlinsdk.model.card.MtgCard
import io.magicthegathering.kotlinsdk.model.set.MtgSet
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

class MtgSetApiClient {

    private object Holder {
        val INSTANCE: MtgSetApi = ApiClientBuilder.instance.create(MtgSetApi::class.java)
    }

    companion object {
        private val instance: MtgSetApi by lazy { MtgSetApiClient.Holder.INSTANCE }

        /**
         * Get all Magic: The Gathering sets.
         *
         * @see <a href="https://docs.magicthegathering.io/#get-all-sets">Get all sets - Endpoint</a>
         * @return Returns an Observable that emits a list containing all of the Magic: The Gathering sets.
         */
        fun getAllSetsObservable(): Observable<List<MtgSet>> {
            return instance.getAllSetsObservable()
        }

        /**
         * Get all Magic: The Gathering sets.
         *
         * @see <a href="https://docs.magicthegathering.io/#get-all-sets">Get all sets - Endpoint</a>
         * @return Returns a list containing all of the Magic: The Gathering sets.
         */
        fun getAllSets(): Response<List<MtgSet>> {
            return instance.getAllSets().execute()
        }

        /**
         * Get a specific Magic: The Gathering set.
         *
         * @see <a href="https://docs.magicthegathering.io/#get-a-specific-set">Get a specific set - Endpoint</a>
         * @param setCode The set code. For example 'mm2' (Modern Masters 2015)
         * @return Returns an Observable that emits a specific Magic: The Gathering set.
         */
        fun getSpecificSetObservable(@Path("setCode") setCode: String): Observable<MtgSet> {
            return instance.getSpecificSetObservable(setCode)
        }

        /**
         * Get a specific Magic: The Gathering set.
         *
         * @see <a href="https://docs.magicthegathering.io/#get-a-specific-set">Get a specific set - Endpoint</a>
         * @param setCode The set code. For example 'mm2' (Modern Masters 2015)
         * @return Returns a specific Magic: The Gathering set.
         */
        fun getSpecificSet(@Path("setCode") setCode: String): Response<MtgSet> {
            return instance.getSpecificSet(setCode).execute()
        }

        /**
         * Generates a booster pack by a specific set code.
         *
         * @see <a href="https://docs.magicthegathering.io/#generate-a-booster-pack">Generate a booster pack - Endpoint</a>
         * @param setCode The set code. For example 'mm2' (Modern Masters 2015)
         * @return Returns an Observable that emits a booster pack with 15 random cards.
         */
        fun generateBoosterPackBySetCodeObservable(setCode: String): Observable<List<MtgCard>> {
            return instance.generateBoosterPackBySetCodeObservable(setCode)
        }

        /**
         * Generates a booster pack by a specific set code.
         *
         * @see <a href="https://docs.magicthegathering.io/#generate-a-booster-pack">Generate a booster pack - Endpoint</a>
         * @param setCode The set code. For example 'mm2' (Modern Masters 2015)
         * @return Returns a booster pack with 15 random cards.
         */
        fun generateBoosterPackBySetCode(setCode: String): Response<List<MtgCard>> {
            return instance.generateBoosterPackBySetCode(setCode).execute()
        }
    }

    private interface MtgSetApi {

        @GET("sets")
        fun getAllSetsObservable(): Observable<List<MtgSet>>

        @GET("sets")
        fun getAllSets(): Call<List<MtgSet>>

        @GET("sets/{setCode}")
        fun getSpecificSetObservable(@Path("setCode") setCode: String): Observable<MtgSet>

        @GET("sets/{setCode}")
        fun getSpecificSet(@Path("setCode") setCode: String): Call<MtgSet>

        @GET("sets/{setCode}/booster")
        fun generateBoosterPackBySetCodeObservable(@Path("setCode") setCode: String): Observable<List<MtgCard>>

        @GET("sets/{setCode}/booster")
        fun generateBoosterPackBySetCode(@Path("setCode") setCode: String): Call<List<MtgCard>>
    }
}