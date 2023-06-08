package com.kfc.restorater.data

import androidx.databinding.ObservableField
import com.kfc.restorater.model.review.Review
import com.kfc.restorater.repo.RetrofitWebServiceFactory
import com.kfc.restorater.repo.api.ReviewRepo

class ReviewRepository {
    var currentReview: ObservableField<Review> = ObservableField()

    private val ratingWebService: ReviewRepo = RetrofitWebServiceFactory.build(
        ReviewRepo::class.java)

    fun getReview(id: Int) : ObservableField<Review> {
        val returnReview: ObservableField<Review> = ObservableField()

        ratingWebService.getReview(id)
            .subscribeOn(io.reactivex.schedulers.Schedulers.io())
            .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
            .subscribe(
                { review ->
                    returnReview.set(review)
                },
                { _ ->
                    returnReview.set(null)
                }
            )

        return returnReview
    }

    fun setCurrentReview(review: Review) {
        currentReview.set(review)
    }

    fun setCurrentReview(review: Int) {
        currentReview.set(getReview(review).get())
    }
}