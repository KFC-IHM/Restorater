package com.kfc.restorater.ui.comment

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import androidx.navigation.NavController
import com.kfc.restorater.data.LoginRepository
import com.kfc.restorater.data.ReviewRepository
import com.kfc.restorater.model.review.Review
import com.kfc.restorater.repo.RetrofitWebServiceFactory
import com.kfc.restorater.repo.api.CommentApi
import com.kfc.restorater.repo.api.ReviewApi
import com.kfc.restorater.repo.api.UserApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class CommentPageViewModel(val loginRepository: LoginRepository, reviewRepository: ReviewRepository) : BaseObservable() {
    var comment = ObservableField(reviewRepository.currentReview.get()!!)
    private val userWebservice = RetrofitWebServiceFactory.build(UserApi::class.java)
    private val reviewWebService = RetrofitWebServiceFactory.build(ReviewApi::class.java)
    val user = ObservableField<String>("loading...")
    var navController: NavController? = null
    init {
        userWebservice.getUser(comment.get()!!.author)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { user ->
                    this.user.set("Commentaire de ${user.username}")
                },
                { _ ->
                    this.user.set("Anonymous")
                }
            )
    }

    fun getRating(): String {
        return comment.get()!!.rating.toString()
    }

    fun deleteComment() {
        comment.get()!!.id?.let {
            reviewWebService.deleteReview(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d("CommentPageViewModel", "deleteComment: success")
                    navController?.navigateUp()
                }
        }
    }
}