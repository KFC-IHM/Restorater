package com.kfc.restorater.repo

import android.database.Observable
import com.kfc.restorater.model.Restaurant
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RestaurantRepo {
    @GET("restaurants/")
    fun fetchRestaurants(): Observable<List<Restaurant>>

    @GET("restaurants/{id}")
    fun fetchRestaurant(@Path("id") id: Int): Observable<Restaurant>

    @POST("restaurants/")
    fun createRestaurant(
        @Body restaurant: Restaurant,
    ): Observable<Restaurant>
}