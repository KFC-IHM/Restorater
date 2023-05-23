package com.kfc.restorater.ui.login

import android.util.Log
import android.util.Patterns
import androidx.databinding.BaseObservable
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import com.kfc.restorater.data.LoginRepository
import com.kfc.restorater.model.users.ApiErrorAuth
import com.kfc.restorater.repo.RetrofitWebServiceGenerator
import io.reactivex.disposables.Disposable

class LoginViewModel(private val loginRepository: LoginRepository) : BaseObservable() {

    var username = ObservableField("")

    var password = ObservableField("")

    var error = ObservableField<String>()

    var success = ObservableField<Boolean>()

    var loading = ObservableField<Boolean>()

    var valid = ObservableField<Boolean>(false)

    var loginResultDisposable: Disposable? = null

    fun login() {
        // can be launched in a separate asynchronous job
        loading.set(true)
        Log.d("LoginViewModel", "login: ${username.get()}")
         loginResultDisposable =  loginRepository.login(username.get()!!, password.get()!!)
            .subscribe(
                { user ->
                    if (user != null) {
                        success.set(true)
                        loading.set(false)
                    } else {
                        error.set("Login failed")
                        loading.set(false)
                    }
                },
                { error ->

                    Log.d("LoginViewModel", "login: ${error.message}")
                    val errorApi = RetrofitWebServiceGenerator.exceptionHandler(
                        error,
                        ApiErrorAuth::class.java
                    )
                    this.error.set(errorApi.detail)
                    loading.set(false)
                }
            )
    }

    fun validate() {
        Log.d("LoginViewModel", "isValid: ${username.get()}")
        valid.set(isUserNameValid(username.get()!!) && isPasswordValid(password.get()!!))
    }


    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains("@")) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 2
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        super.removeOnPropertyChangedCallback(callback)
        loginResultDisposable?.dispose()
    }
}