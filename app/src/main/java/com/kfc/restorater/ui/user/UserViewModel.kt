package com.kfc.restorater.ui.user

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import com.kfc.restorater.data.LoginRepository
import com.kfc.restorater.repo.RetrofitWebServiceFactory
import com.kfc.restorater.repo.api.UserApi

class UserViewModel() : BaseObservable() {

    val username = ObservableField("Loading...")

    val listTitle = ObservableField("Loading...")

    private val userWebService = RetrofitWebServiceFactory.build(UserApi::class.java)

    var loginRepository: LoginRepository = LoginRepository()

    constructor(loginRepository: LoginRepository) : this() {
        this.loginRepository = loginRepository

        userWebService.getUser(loginRepository.user.get()!!.userId)
            .subscribeOn(io.reactivex.schedulers.Schedulers.io())
            .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
            .subscribe(
                { user ->
                    username.set("Bonjour, ${user.username}!")
                    if (user.is_moderator) {
                        listTitle.set("Commentaires à modérer")
                    }
                },
                { _ ->
                    username.set("Bonjour, User!")
                }
            )
    }
}
