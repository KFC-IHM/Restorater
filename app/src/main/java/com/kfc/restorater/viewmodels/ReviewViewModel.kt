package com.kfc.restorater.viewmodels

import com.kfc.restorater.model.review.Review
import com.kfc.restorater.repo.RetrofitWebServiceGenerator
import com.kfc.restorater.repo.api.ReviewRepo
import com.kfc.restorater.repo.api.UserRepo
import io.reactivex.Observable

class ReviewViewModel {
    val reviewRepo = RetrofitWebServiceGenerator().createService(ReviewRepo::class.java)

    fun getReviews(): Observable<List<Review>>? {
        return reviewRepo.getReviews()
    }

    fun getReview(id: Int): Observable<Review>? {
        return reviewRepo.getReview(id)
    }

    fun createReview(review: Review): Observable<Review>? {
        return reviewRepo.createReview(review)
    }

    fun updateReview(id: Int, review: Review): Observable<Review>? {
        return reviewRepo.updateReview(id, review)
    }

    fun deleteReview(id: Int): Observable<Review>? {
        return reviewRepo.deleteReview(id)
    }
}