package com.kfc.restorater.viewmodels

import android.arch.lifecycle.ViewModel
import com.kfc.restorater.model.restaurant.Restaurant
import com.kfc.restorater.repo.api.RestaurantRepo
import com.kfc.restorater.repo.RetrofitWebServiceGenerator
import io.reactivex.Observable

class RestaurantViewModel : ViewModel() {
    private val restaurantRepo = RetrofitWebServiceGenerator().createService(RestaurantRepo::class.java)
    private var observableRestaurants: Observable<List<Restaurant>>? = null

    fun getRestaurants(): Observable<List<Restaurant>>? {
        if (observableRestaurants == null) {
            observableRestaurants = restaurantRepo.getRestaurants()
        }

        return observableRestaurants
    }
}
