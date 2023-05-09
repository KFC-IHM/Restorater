package com.kfc.restorater.factory

import com.kfc.restorater.model.RestaurantModel

class RestaurantFactory {
    companion object {
        fun createRestaurant(name: String, address: String, phone: String?): RestaurantModel {
            return RestaurantModel(name, address, phone)
        }
    }
}
