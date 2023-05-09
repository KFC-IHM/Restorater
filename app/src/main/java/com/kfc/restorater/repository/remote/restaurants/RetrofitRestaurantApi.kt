package com.kfc.restorater.repository.remote.restaurants

import com.kfc.restorater.data.restaurants.Restaurant
import retrofit2.Call
import retrofit2.http.*

interface RetrofitRestaurantApi {
    @GET("restaurants/")
    fun getRestaurants(): Call<List<Restaurant>>

    @GET("restaurants/{id}")
    fun getRestaurant(@Path("id") id: Int): Call<Restaurant>

    @POST("restaurants/")
    fun createRestaurant(
        @Body restaurant: Restaurant,
    ): Call<Restaurant>
}