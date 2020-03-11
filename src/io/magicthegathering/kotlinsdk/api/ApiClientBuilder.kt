package io.magicthegathering.kotlinsdk.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.magicthegathering.kotlinsdk.api.deserializer.ItemTypeAdapterFactory
import io.magicthegathering.kotlinsdk.api.deserializer.MtgCardDeserializer
import io.magicthegathering.kotlinsdk.api.deserializer.MtgSetDeserializer
import io.magicthegathering.kotlinsdk.model.card.MtgCard
import io.magicthegathering.kotlinsdk.model.set.MtgSet
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClientBuilder private constructor() {

    private object Holder {
        val gson: Gson = GsonBuilder()
                .registerTypeAdapter(MtgCard::class.java, MtgCardDeserializer())
                .registerTypeAdapter(MtgSet::class.java, MtgSetDeserializer())
                .registerTypeAdapterFactory(ItemTypeAdapterFactory())
                .create()

        val okHttpClient: OkHttpClient = OkHttpClient()
        .newBuilder().build()

        var m_client =  OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build();
        

        val INSTANCE: Retrofit = Retrofit.Builder()
                .baseUrl("https://api.magicthegathering.io/v1/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(m_client)
                .build()
    }

    companion object {
        val instance: Retrofit by lazy { Holder.INSTANCE }
    }
}
