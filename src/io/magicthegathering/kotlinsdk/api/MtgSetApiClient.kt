package io.magicthegathering.kotlinsdk.api

import io.magicthegathering.kotlinsdk.model.card.MtgCard
import io.magicthegathering.kotlinsdk.model.set.MtgSet
import io.reactivex.Observable
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
         * @see <a href="https://docs.magicthegathering.io/#get-all-sets">Get All Sets - Endpoint</a>
         * @return Returns an Observable that emits a list containing all of the Magic: The Gathering sets.
         */
        fun getAllSets(): Observable<List<MtgSet>> {
            return instance.getAllSets()
        }

        /**
         * Get a specific Magic: The Gathering set.
         *
         * @see <a href="https://docs.magicthegathering.io/#get-a-specific-set">Get a Specific Set - Endpoint</a>
         * @param setCode The set code. For example 'mm2' (Modern Masters 2015)
         * @return Returns an Observable that emits a specific Magic: The Gathering set.
         */
        fun getSpecificSet(@Path("setCode") setCode: String): Observable<MtgSet> {
            return instance.getSpecificSet(setCode)
        }

        /**
         * Generates a booster pack by a specific set code.
         *
         * @see <a href="https://docs.magicthegathering.io/#generate-a-booster-pack">Generate a Booster Pack - Endpoint</a>
         * @param setCode The set code. For example 'mm2' (Modern Masters 2015)
         * @return Returns an Observable that emits a booster pack with 15 random cards.
         */
        fun generateBoosterPackBySetCode(setCode: String): Observable<List<MtgCard>> {
            return instance.generateBoosterPackBySetCode(setCode)
        }
    }

    private interface MtgSetApi {

        @GET("sets")
        fun getAllSets(): Observable<List<MtgSet>>

        @GET("sets/{setCode}")
        fun getSpecificSet(@Path("setCode") setCode: String): Observable<MtgSet>

        @GET("sets/{setCode}/booster")
        fun generateBoosterPackBySetCode(@Path("setCode") setCode: String): Observable<List<MtgCard>>
    }
}