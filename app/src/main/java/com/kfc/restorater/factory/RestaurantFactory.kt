package com.kfc.restorater.factory

class RestaurantFactory {
    companion object {
        fun createRestaurant(name: String, address: String, phone: String?): RestaurantModel {
            return RestaurantModel(name, address, phone)
        }
    }
}
