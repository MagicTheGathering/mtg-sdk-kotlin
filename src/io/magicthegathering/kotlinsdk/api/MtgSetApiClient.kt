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
         * Generates a booster pack by an specific set code.
         *
         * @see <a href="https://docs.magicthegathering.io/#generate-a-booster-pack">Generate a Booster Pack - Endpoint</a>
         * @param setCode The set code. For example 'mm2' (Modern Masters 2015)
         * @return Returns an Observable that emits a booster pack with 15 random cards.
         */
        fun generateBoosterPackBySetCode(setCode: String): Observable<List<MtgCard>> {
            return instance.generateBoosterPackBySetCode(setCode)
        }

        /**
         * Get all Magic: The Gathering sets.
         *
         * @see <a href="https://docs.magicthegathering.io/#get-all-sets">Get All Sets - Endpoint</a>
         * @return Returns an Observable that emits a list containing all of the Magic: The Gathering sets
         */
        fun getAllSets(): Observable<List<MtgSet>> {
            return instance.getAllSets()
        }
    }

    private interface MtgSetApi {

        @GET("sets/{setCode}/booster")
        fun generateBoosterPackBySetCode(@Path("setCode") setCode: String): Observable<List<MtgCard>>

        @GET("sets")
        fun getAllSets(): Observable<List<MtgSet>>
    }
}