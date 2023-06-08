package com.kfc.restorater.factory

import androidx.databinding.BaseObservable
import com.kfc.restorater.data.LoginRepository
import com.kfc.restorater.data.RestaurantRepository
import com.kfc.restorater.recyclers.comment.CommentViewModel
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
            if (modelClass.isAssignableFrom(com.kfc.restorater.recyclers.restaurant.RestaurantViewModel::class.java)) {
                return com.kfc.restorater.recyclers.restaurant.RestaurantViewModel(
                    restaurantRepository = restaurantRepository
                ) as T
            }
            if (modelClass.isAssignableFrom(com.kfc.restorater.recyclers.restaurant.RestaurantViewModel::class.java)) {
                return com.kfc.restorater.recyclers.restaurant.RestaurantViewModel(
                    restaurantRepository = restaurantRepository
                ) as T
            }
            if (modelClass.isAssignableFrom(com.kfc.restorater.ui.restaurant.RestaurantPageViewModel::class.java)) {
                return com.kfc.restorater.ui.restaurant.RestaurantPageViewModel(
                    restaurantRepository = restaurantRepository
                ) as T
            }
            if (modelClass.isAssignableFrom(com.kfc.restorater.recyclers.restaurantComments.RestaurantCommentViewModel::class.java)) {
                return com.kfc.restorater.recyclers.restaurantComments.RestaurantCommentViewModel(
                    restaurantRepository = restaurantRepository
                ) as T
            }
            if (modelClass.isAssignableFrom(com.kfc.restorater.ui.comment.CommentPageViewModel::class.java)) {
                return com.kfc.restorater.ui.comment.CommentPageViewModel(
                    loginRepository = loginRepository
                ) as T
            }


            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}