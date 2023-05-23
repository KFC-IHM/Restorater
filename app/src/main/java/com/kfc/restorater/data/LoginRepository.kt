package com.kfc.restorater.data

import androidx.databinding.ObservableField
import com.auth0.android.jwt.JWT
import com.kfc.restorater.data.model.LoggedInUser
import com.kfc.restorater.model.users.Credentials
import com.kfc.restorater.repo.RetrofitWebServiceGenerator
import com.kfc.restorater.repo.api.RetrofitAuthApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository {
    private var authWebservice: RetrofitAuthApi = RetrofitWebServiceGenerator.createService(
        RetrofitAuthApi::class.java
    )
    val user = ObservableField<LoggedInUser>()

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
                    return@map user
                } else {
                    // error
                    return@map null
                }
            }


    }
}