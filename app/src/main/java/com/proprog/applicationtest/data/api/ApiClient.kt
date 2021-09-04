package com.proprog.applicationtest.data.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

public object ApiClient {
    private const val BASE_URL = "https://api.stackexchange.com/2.3/"
    private const val TIMEOUT = 60L

    val API_SERVICE: ApiService = getRetrofitClient().create(ApiService::class.java)

    private fun getRetrofitClient(): Retrofit {
        val gson = GsonBuilder()
            .setPrettyPrinting()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(getOkHttpClient())
            .build()
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    }

    private fun getOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

}