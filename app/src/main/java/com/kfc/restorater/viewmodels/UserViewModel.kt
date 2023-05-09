package com.kfc.restorater.viewmodels

import androidx.databinding.BaseObservable
import com.auth0.android.jwt.JWT
import com.kfc.restorater.model.users.Credentials
import com.kfc.restorater.model.users.User
import com.kfc.restorater.repo.RetrofitWebServiceGenerator
import com.kfc.restorater.repo.api.RetrofitAuthApi
import com.kfc.restorater.repo.api.UserRepo
import io.reactivex.Observable

class UserViewModel : BaseObservable() {
    val userRepo = RetrofitWebServiceGenerator().createService(UserRepo::class.java)
    val authRepo = RetrofitWebServiceGenerator().createService(RetrofitAuthApi::class.java)
    var user: User? = null


    fun login(credentials: Credentials): Observable<User>? {
        return authRepo.login(credentials).flatMap { jwt ->
            val decodedJWT = JWT(jwt.access)
            val userId = decodedJWT.getClaim("user_id").asInt()
            if (userId != null) {
                userRepo.getUser(userId).map { user ->
                    this.user = user
                    user
                }
            } else {
                Observable.error(Throwable("Invalid JWT"))
            }
        }
    }

    fun getUsers(): Observable<List<User>>? {
        return userRepo.getUsers()
    }

    fun getUser(id: Int): Observable<User>? {
        return userRepo.getUser(id)
    }

    fun createUser(user: User): Observable<User>? {
        return userRepo.createUser(user)
    }

    fun updateUser(id: Int, user: User): Observable<User>? {
        return userRepo.updateUser(id, user)
    }

    fun deleteUser(id: Int): Observable<User>? {
        return userRepo.deleteUser(id)
    }
}