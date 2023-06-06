package com.kfc.restorater.recyclers.restaurant

import androidx.databinding.BaseObservable
import com.kfc.restorater.data.RestaurantRepository
import com.kfc.restorater.model.restaurant.Restaurant

class RestaurantViewModel(val restaurantRepository: RestaurantRepository) : BaseObservable() {
    fun onRestaurantClicked(restaurant: Restaurant) {
        restaurantRepository.setCurrentRestaurant(restaurant)
    }

    fun onRestaurantClicked(id: Int) {
        restaurantRepository.setCurrentRestaurant(id)
    }
}