package com.kfc.restorater.viewmodels

import android.text.TextUtils
import android.util.Patterns
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.kfc.restorater.model.User
import com.kfc.restorater.BR

class LoginViewModel : BaseObservable() {
    private val user: User = User("", "")
    private val successMessage = "Login was successful"
    private val errorMessage = "Email or Password not valid"

    @get:Bindable
    var toastMessage: String? = null
        private set

    private fun setToastMessage(toastMessage: String) {
        this.toastMessage = toastMessage
        notifyPropertyChanged(BR.toastMessage)
    }

    @get:Bindable
    var userEmail: String?
        get() = user.getEmail()
        set(email) {
            user.setEmail(email)
            notifyPropertyChanged(BR.userEmail)
        }

    @get:Bindable
    var userPassword: String?
        get() = user.getPassword()
        set(password) {
            user.setPassword(password)
            notifyPropertyChanged(BR.userPassword)
        }

    fun onLoginClicked() {
        if (isInputDataValid) setToastMessage(successMessage) else setToastMessage(errorMessage)
    }

    val isInputDataValid: Boolean
        get() = !TextUtils.isEmpty(userEmail) && Patterns.EMAIL_ADDRESS.matcher(userEmail)
            .matches() && userPassword!!.length > 5
}