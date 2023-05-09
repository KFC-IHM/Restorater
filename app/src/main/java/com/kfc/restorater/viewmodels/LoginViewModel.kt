package com.kfc.restorater.viewmodels

import androidx.databinding.BaseObservable
import com.kfc.restorater.model.users.User
import com.kfc.restorater.repo.RetrofitWebServiceGenerator
import com.kfc.restorater.repo.api.UserRepo
import io.reactivex.Observable

class UserViewModel : BaseObservable() {
    val userRepo = RetrofitWebServiceGenerator().createService(UserRepo::class.java)
    var observableUsers: Observable<List<User>>? = null

    fun getUsers(): Observable<List<User>>? {
        if (observableUsers == null) {
            observableUsers = userRepo.getUsers()
        }

        return observableUsers
    }
}