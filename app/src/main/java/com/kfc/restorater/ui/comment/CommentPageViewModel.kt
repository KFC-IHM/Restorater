package com.kfc.restorater.ui.comment

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import com.kfc.restorater.data.LoginRepository
import com.kfc.restorater.repo.RetrofitWebServiceFactory
import com.kfc.restorater.repo.api.UserApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class CommentPageViewModel(val loginRepository: LoginRepository) : BaseObservable() {
    var comment = ObservableField(loginRepository.review.get()!!)
    private val userWebservice = RetrofitWebServiceFactory.build(UserApi::class.java)
    val user = ObservableField<String>("loading...")
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
}