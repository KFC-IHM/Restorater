package com.kfc.restorater.repository.remote.restaurants

import com.kfc.restorater.data.users.Credentials
import com.kfc.restorater.data.users.JWT
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitAuthApi {
    @POST("/api-auth-jwt/login/")
    fun login(
        @Body body: Credentials,
    ): Call<JWT>
}