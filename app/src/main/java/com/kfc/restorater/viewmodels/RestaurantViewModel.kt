package com.kfc.restorater.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.kfc.restorater.model.Restaurant
import com.kfc.restorater.repo.RestaurantRepo

class RestaurantViewModel : ViewModel() {
    var restaurantRepo: RestaurantRepo = RestaurantRepo()
    var mutableRestaurants: MutableLiveData<List<Restaurant>> = MutableLiveData()

    fun getRestaurants() {
        if (mutableRestaurants.value == null) {
            mutableRestaurants = restaurantRepo!!.fetchRestaurants()
        }
    }
}
