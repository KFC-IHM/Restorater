package com.kfc.restorater.repo.api

import com.kfc.restorater.model.users.User
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserApi {

    @GET("users/")
    fun getUsers(): Observable<List<User>>

    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Observable<User>

    @POST("users/")
    fun createUser(
        @Body user: User,
    ): Observable<User>

    @PUT("users/{id}")
    fun updateUser(
        @Path("id") id: Int,
        @Body user: User,
    ): Observable<User>

    @DELETE("users/{id}")
    fun deleteUser(@Path("id") id: Int): Observable<User>

}