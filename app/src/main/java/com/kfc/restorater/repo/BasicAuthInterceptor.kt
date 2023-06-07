package com.kfc.restorater.repo

import android.util.Log
import okhttp3.Credentials
import okhttp3.Interceptor

class BasicAuthInterceptor(username: String, password: String) : Interceptor {
    private var credentials: String = Credentials.basic(username, password)

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        Log.d("BasicAuthInterceptor", "intercept: $request")
        request = request.newBuilder().header("Authorization", credentials).build()
        return chain.proceed(request)
    }
}