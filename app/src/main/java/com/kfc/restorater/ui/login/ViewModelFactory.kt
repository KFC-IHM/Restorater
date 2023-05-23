package com.kfc.restorater.ui.login

import androidx.databinding.BaseObservable
import com.kfc.restorater.data.LoginRepository

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class ViewModelFactory {
    companion object {
        val loginRepository = LoginRepository()

        @Suppress("UNCHECKED_CAST")
        fun <T : BaseObservable> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                return LoginViewModel(
                    loginRepository = loginRepository
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}