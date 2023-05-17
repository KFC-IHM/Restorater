package com.kfc.restorater.repo

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


const val API_BASE_URL = "https://restorater.ozeliurs.com/api/"

class RetrofitWebServiceGenerator {
    companion object {


        private val httpClient =
            OkHttpClient.Builder().addInterceptor(BasicAuthInterceptor("admin", "password"))

        private val moshi: Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .build()

        fun <S> createService(serviceClass: Class<S>?): S {
            return retrofit.create(serviceClass!!)
        }

        fun <S> exceptionHandler(throwable: Throwable, errorBodyType: Class<S>): S {
            when (throwable) {
                is HttpException -> {
                    if (throwable.response().errorBody() != null) {
                        val errorBody = throwable.response()?.errorBody()?.string()
                        val jsonAdapter = moshi.adapter(errorBodyType)
                        return jsonAdapter.fromJson(errorBody!!)!!
                    } else {
                        throw throwable
                    }

                }

                else -> throw throwable
            }
        }
    }
}

