package com.kfc.restorater.repo.api

import com.kfc.restorater.model.restaurant.Restaurant
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RestaurantApi {
    @GET("restaurants/")
    fun getRestaurants(): Observable<List<Restaurant>>

    @GET("restaurants/{id}")
    fun getRestaurant(@Path("id") id: Int): Observable<Restaurant>

    @POST("restaurants/")
    fun createRestaurant(
        @Body restaurant: Restaurant,
    ): Observable<Restaurant>

    @PUT("restaurants/{id}")
    fun updateRestaurant(
        @Path("id") id: Int,
        @Body restaurant: Restaurant,
    ): Observable<Restaurant>

    @DELETE("restaurants/{id}")
    fun deleteRestaurant(@Path("id") id: Int): Observable<Restaurant>
}