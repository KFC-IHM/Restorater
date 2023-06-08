package com.kfc.restorater.data

import android.util.Log
import androidx.databinding.ObservableField
import com.auth0.android.jwt.JWT
import com.kfc.restorater.data.model.LoggedInUser
import com.kfc.restorater.model.review.Review
import com.kfc.restorater.model.users.Credentials
import com.kfc.restorater.model.users.User
import com.kfc.restorater.repo.RetrofitWebServiceFactory
import com.kfc.restorater.repo.api.AuthApi
import com.kfc.restorater.repo.api.UserApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository {
    private var authWebservice: AuthApi = RetrofitWebServiceFactory.build(
        AuthApi::class.java
    )
    val user = ObservableField<LoggedInUser>()
    val userData = ObservableField<User>()
    val userWebService = RetrofitWebServiceFactory.build(UserApi::class.java)
    val review = ObservableField<Review>()

    fun login(username: String, password: String): Single<ObservableField<LoggedInUser>> {
        val credentials = Credentials(username, password)
        return authWebservice.login(credentials)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { jwt ->
                val decodedJWT = JWT(jwt.access)
                val userId = decodedJWT.getClaim("user_id").asInt()
                if (userId != null) {
                    user.set(LoggedInUser(userId, jwt))
                    user.get()?.let {
                        userWebService.getUser(it.userId)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                { user ->
                                    userData.set(user)
                                },
                                { _ ->
                                    userData.set(null)
                                    Log.d("LoginRepository", "login: critical error")
                                }
                            )
                    }
                    return@map user
                } else {
                    // error
                    return@map null
                }
            }


    }

    fun setCurrentReview(review: Review) {
        this.review.set(review)
    }
}