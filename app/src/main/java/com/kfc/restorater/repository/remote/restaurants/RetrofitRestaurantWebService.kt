package com.kfc.restorater.repository.remote.restaurants

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class RetrofitRestaurantWebService {
    private val API_BASE_URL = "https://restorater.ozeliurs.com/api/"

    private val httpClient =
        OkHttpClient.Builder().addInterceptor(BasicAuthInterceptor("admin", "password"))
    val api: RetrofitRestaurantApi by lazy {
        createRestaurantApi()
    }

    private fun createRestaurantApi(): RetrofitRestaurantApi {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(httpClient.build())
            .build()

        return retrofit.create(RetrofitRestaurantApi::class.java)
    }
}