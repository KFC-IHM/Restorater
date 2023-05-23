package com.kfc.restorater.ui.user

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import com.kfc.restorater.data.LoginRepository
import com.kfc.restorater.repo.RetrofitWebServiceGenerator
import com.kfc.restorater.repo.api.UserRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserViewModel(val loginRepository: LoginRepository) : BaseObservable() {

    val username = ObservableField("Bonjour, User!")
    private var userWebService = RetrofitWebServiceGenerator.createService(UserRepo::class.java)

    init {
        loginRepository.user.get()?.let {
            userWebService.getUser(it.userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                { user ->
                    username.set("Bonjour, ${user.username}!")
                },
                { _ ->
                    username.set("Bonjour, ${it.userId}!")
                }
            )
        }
    }
}