package com.kfc.restorater.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.kfc.restorater.data.restaurant.Restaurant
import com.kfc.restorater.repo.RestaurantRepo
import com.kfc.restorater.repo.api.RetrofitWebServiceGenerator

class RestaurantViewModel : ViewModel() {
    val restaurantRepo = RetrofitWebServiceGenerator().createService(RestaurantRepo::class.java)
    var mutableRestaurants: MutableLiveData<List<Restaurant>> = MutableLiveData()

    fun getRestaurants() {
        if (mutableRestaurants.value == null) {
            mutableRestaurants = restaurantRepo.getRestaurants()
        }
    }
}
