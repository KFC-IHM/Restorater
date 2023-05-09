package com.kfc.restorater.factory

import com.kfc.restorater.model.restaurant.Restaurant

class RestaurantFactory {
    companion object {
        fun createRestaurant(
            id: Int? = null,
            name: String,
            description: String,
            image: String? = null,
            owner: Int,
        ): Restaurant {
            return Restaurant(id, name, description, image, owner)
        }
    }
}
