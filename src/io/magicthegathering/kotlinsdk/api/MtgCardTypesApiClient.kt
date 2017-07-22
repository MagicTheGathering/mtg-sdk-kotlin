package io.magicthegathering.kotlinsdk.api

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

class MtgCardTypesApiClient {

    private object Holder {
        val INSTANCE: MtgCardTypesApi = ApiClientBuilder.instance.create(MtgCardTypesApi::class.java)
    }

    companion object {
        private val instance: MtgCardTypesApi by lazy { MtgCardTypesApiClient.Holder.INSTANCE }

        /**
         * Get all Magic: The Gathering card types.
         *
         * @see <a href="https://docs.magicthegathering.io/#get-all-types">Get all card types - Endpoint</a>
         * @return Returns an Observable that emits a list containing all of the Magic: The Gathering card types.
         */
        fun getAllTypesObservable(): Observable<List<String>> {
            return instance.getAllTypesObservable()
        }

        /**
         * Get all Magic: The Gathering card types.
         *
         * @see <a href="https://docs.magicthegathering.io/#get-all-types">Get all card types - Endpoint</a>
         * @return Returns a list containing all of the Magic: The Gathering card types.
         */
        fun getAllTypes(): Response<List<String>> {
            return instance.getAllTypes().execute()
        }
    }

    private interface MtgCardTypesApi {

        @GET("types")
        fun getAllTypesObservable(): Observable<List<String>>

        @GET("types")
        fun getAllTypes(): Call<List<String>>
    }
}