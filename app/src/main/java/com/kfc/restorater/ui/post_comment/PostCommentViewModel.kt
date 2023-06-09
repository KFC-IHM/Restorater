package com.kfc.restorater.ui.post_comment

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import com.kfc.restorater.data.LoginRepository
import com.kfc.restorater.data.RestaurantRepository
import com.kfc.restorater.data.ReviewRepository
import com.kfc.restorater.model.review.Review

class PostCommentViewModel(
    val loginRepository: LoginRepository,
    val restaurantRepository: RestaurantRepository,
    val reviewRepository: ReviewRepository
) : BaseObservable() {

    val commentTitle : ObservableField<String> = ObservableField("")
    val commentBody : ObservableField<String> = ObservableField("")


    fun postComment() {
        if (commentTitle.get().isNullOrEmpty() || commentBody.get().isNullOrEmpty()) {
            Log.d("PostCommentViewModel", "postComment: empty")
            return
        }

        val review = Review(
            title = commentTitle.get()!!,
            description = commentBody.get()!!,
            rating = 5,
            restaurant = restaurantRepository.currentRestaurant.get()!!.id!!,
            author = loginRepository.user.get()!!.userId

        )

        reviewRepository.postReview(review)
    }
}