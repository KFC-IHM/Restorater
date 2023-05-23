package com.kfc.restorater.ui.user

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import com.kfc.restorater.data.LoginRepository

class UserViewModel(val loginRepository: LoginRepository) : BaseObservable() {

    var username = ObservableField("")
}