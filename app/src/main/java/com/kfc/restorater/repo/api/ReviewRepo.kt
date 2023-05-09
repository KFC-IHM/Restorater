package com.kfc.restorater.repo.api

import io.reactivex.Observable
import com.kfc.restorater.model.review.Review
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ReviewRepo {
    @GET("reviews/")
    fun getReviews(): Observable<List<Review>>

    @GET("reviews/{id}")
    fun getReview(@Path("id") id: Int): Observable<Review>

    @POST("reviews/")
    fun createReview(
        @Body review: Review,
    ): Observable<Review>

    @PUT("reviews/{id}")
    fun updateReview(
        @Path("id") id: Int,
        @Body review: Review,
    ): Observable<Review>

    @DELETE("reviews/{id}")
    fun deleteReview(@Path("id") id: Int): Observable<Review>
}