package com.kfc.restorater.repo.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


const val API_BASE_URL = "https://restorater.ozeliurs.com/api/"

class RetrofitWebServiceGenerator {

    private val httpClient =
        OkHttpClient.Builder().addInterceptor(BasicAuthInterceptor("admin", "password"))

    fun <S> createService(serviceClass: Class<S>?): S {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(httpClient.build())
            .build()

        return retrofit.create(serviceClass!!)
    }
}