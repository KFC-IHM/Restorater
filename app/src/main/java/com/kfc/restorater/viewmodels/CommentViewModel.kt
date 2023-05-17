package com.kfc.restorater.viewmodels

import com.kfc.restorater.model.comment.Comment
import com.kfc.restorater.repo.RetrofitWebServiceGenerator
import com.kfc.restorater.repo.api.CommentRepo
import io.reactivex.Observable

class CommentViewModel {
    val commentRepo = RetrofitWebServiceGenerator.createService(CommentRepo::class.java)

    fun getComments(): Observable<List<Comment>>? {
        return commentRepo.getComments()
    }

    fun getComment(id: Int): Observable<Comment>? {
        return commentRepo.getComment(id)
    }

    fun createComment(comment: Comment): Observable<Comment>? {
        return commentRepo.createComment(comment)
    }

    fun updateComment(id: Int, comment: Comment): Observable<Comment>? {
        return commentRepo.updateComment(id, comment)
    }

    fun deleteComment(id: Int): Observable<Comment>? {
        return commentRepo.deleteComment(id)
    }
}