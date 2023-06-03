package com.kfc.restorater.factory

import androidx.databinding.BaseObservable
import com.kfc.restorater.data.LoginRepository
import com.kfc.restorater.data.RestaurantRepository
import com.kfc.restorater.recyclers.comment.CommentViewModel
import com.kfc.restorater.recyclers.restaurant.RestaurantViewModel
import com.kfc.restorater.ui.login.LoginViewModel
import com.kfc.restorater.ui.user.UserViewModel

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class ViewModelFactory {
    companion object {
        private val loginRepository = LoginRepository()
        private val restaurantRepository = RestaurantRepository()

        @Suppress("UNCHECKED_CAST")
        fun <T : BaseObservable> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                return LoginViewModel(
                    loginRepository = loginRepository
                ) as T
            }
            if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
                return UserViewModel(
                    loginRepository = loginRepository
                ) as T
            }
            if (modelClass.isAssignableFrom(CommentViewModel::class.java)) {
                return CommentViewModel(
                    loginRepository = loginRepository
                ) as T
            }
            if (modelClass.isAssignableFrom(RestaurantViewModel::class.java)) {
                return RestaurantViewModel(
                    restaurantRepository = restaurantRepository
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}