package com.kfc.restorater.ui.user

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import com.kfc.restorater.data.LoginRepository

class UserViewModel(val loginRepository: LoginRepository) : BaseObservable() {

    val username = ObservableField("Bonjour, User!")

    init {
        loginRepository.userData.addOnPropertyChangedCallback(object :
            androidx.databinding.Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: androidx.databinding.Observable?, propertyId: Int) {
                username.set("Bonjour, " + loginRepository.userData.get()?.username + " !")
            }
        })
    }
}
