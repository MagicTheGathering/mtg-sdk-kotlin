package io.magicthegathering.kotlinsdk.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.magicthegathering.kotlinsdk.api.deserializer.ItemTypeAdapterFactory
import io.magicthegathering.kotlinsdk.api.deserializer.MtgCardDeserializer
import io.magicthegathering.kotlinsdk.model.card.MtgCard
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClientBuilder private constructor() {

    private object Holder {
        val gson: Gson = GsonBuilder()
                .registerTypeAdapter(MtgCard::class.java, MtgCardDeserializer())
                .registerTypeAdapterFactory(ItemTypeAdapterFactory())
                .create()

        val okHttpClient: OkHttpClient = OkHttpClient().newBuilder().build()

        val INSTANCE: Retrofit = Retrofit.Builder()
                .baseUrl("https://api.magicthegathering.io/v1/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build()
    }

    companion object {
        val instance: Retrofit by lazy { Holder.INSTANCE }
    }
}