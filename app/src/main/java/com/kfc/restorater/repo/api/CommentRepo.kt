package com.kfc.restorater.repo.api

import android.database.Observable
import com.kfc.restorater.model.comment.Comment
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CommentRepo {
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

    @DELETE("comments/{id}")
    fun deleteComment(@Path("id") id: Int): Observable<Comment>

}