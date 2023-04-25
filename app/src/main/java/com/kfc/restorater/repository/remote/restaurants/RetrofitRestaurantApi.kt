package com.kfc.restorater.repository.remote.restaurants

import com.kfc.restorater.data.Restaurant
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface RetrofitRestaurantApi {
    @GET("restaurants/")
    fun getRestaurants(): Call<List<Restaurant>>

    @GET("restaurants/{id}")
    fun getRestaurant(@Path("id") id: Int): Call<Restaurant>

    @POST("restaurants/")
    @Multipart
    fun createRestaurant(
        @Part("name") name: String,
        @Part("description") description: String,
        @Part image: MultipartBody.Part,
        @Part("owner") owner: Int,
    ): Call<Restaurant>
}