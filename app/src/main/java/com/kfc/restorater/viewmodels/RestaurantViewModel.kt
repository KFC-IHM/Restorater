package com.kfc.restorater.viewmodels

import com.kfc.restorater.model.restaurant.Restaurant
import com.kfc.restorater.repo.api.RestaurantRepo
import com.kfc.restorater.repo.RetrofitWebServiceGenerator
import io.reactivex.Observable

class RestaurantViewModel {
    val restaurantRepo = RetrofitWebServiceGenerator().createService(RestaurantRepo::class.java)

    fun getRestaurants(): Observable<List<Restaurant>>? {
        return restaurantRepo.getRestaurants()
    }

    fun getRestaurant(id: Int): Observable<Restaurant>? {
        return restaurantRepo.getRestaurant(id)
    }

    fun createRestaurant(restaurant: Restaurant): Observable<Restaurant>? {
        return restaurantRepo.createRestaurant(restaurant)
    }
}
