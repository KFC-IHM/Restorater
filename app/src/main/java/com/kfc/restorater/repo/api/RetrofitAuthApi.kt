package com.kfc.restorater.repo.api

import com.kfc.restorater.model.users.Credentials
import com.kfc.restorater.model.users.JWTModel
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitAuthApi {
    @POST("/api-auth-jwt/login/")
    fun login(
        @Body body: Credentials,
    ): Single<JWTModel>
}