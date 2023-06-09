package com.kfc.restorater.repo.api

import com.kfc.restorater.model.comment.Comment
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CommentApi {
    @GET("comments/")
    fun getComments(): Observable<List<Comment>>

    @GET("comments/{id}")
    fun getComment(@Path("id") id: Int): Observable<Comment>

    @POST("comments/")
    fun createComment(
        @Body comment: Comment,
    ): Observable<Comment>

    @PUT("comments/{id}")
    fun updateComment(
        @Path("id") id: Int,
        @Body comment: Comment,
    ): Observable<Comment>

    @DELETE("comments/{id}/")
    fun deleteComment(@Path("id") id: Int): Single<Comment>

}