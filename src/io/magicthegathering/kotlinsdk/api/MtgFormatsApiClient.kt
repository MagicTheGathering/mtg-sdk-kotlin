package io.magicthegathering.kotlinsdk.api

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

class MtgFormatsApiClient private constructor() {

    private object Holder {
        val INSTANCE: MtgFormatsApi = ApiClientBuilder.instance.create(MtgFormatsApi::class.java)
    }

    companion object {
        private val instance: MtgFormatsApi by lazy { MtgFormatsApiClient.Holder.INSTANCE }

        /**
         * Get all Magic: The Gathering game formats.
         *
         * @see <a href="https://docs.magicthegathering.io/#formats">Get all game formats - Endpoint</a>
         * @return Returns an Observable that emits a list containing all of the Magic: The Gathering game formats.
         */
        fun getAllFormatsObservable(): Observable<List<String>> {
            return instance.getAllFormatsObservable()
        }

        /**
         * Get all Magic: The Gathering game formats.
         *
         * @see <a href="https://docs.magicthegathering.io/#formats">Get all game formats - Endpoint</a>
         * @return Returns a list containing all of the Magic: The Gathering game formats.
         */
        fun getAllFormats(): Response<List<String>> {
            return instance.getAllFormats().execute()
        }
    }

    private interface MtgFormatsApi {

        @GET("formats")
        fun getAllFormatsObservable(): Observable<List<String>>

        @GET("formats")
        fun getAllFormats(): Call<List<String>>
    }
}