package com.kfc.restorater.data

import android.util.Log
import androidx.databinding.ObservableField
import com.kfc.restorater.model.restaurant.Restaurant
import com.kfc.restorater.repo.RetrofitWebServiceFactory
import com.kfc.restorater.repo.api.RestaurantApi

class RestaurantRepository {
    var restaurants: ObservableField<List<Restaurant>> = ObservableField()

    var currentRestaurant: ObservableField<Restaurant> = ObservableField()

    private val restaurantWebService: RestaurantApi = RetrofitWebServiceFactory.build(
        RestaurantApi::class.java)

    init {
        restaurantWebService.getRestaurants()
            .subscribeOn(io.reactivex.schedulers.Schedulers.io())
            .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
            .subscribe(
                { restaurants ->
                    this.restaurants.set(restaurants)
                },
                { _ ->
                    this.restaurants.set(null)
                }
            )
    }

    fun getRestaurant(id: Int) : ObservableField<Restaurant> {
        val returnRestaurant: ObservableField<Restaurant> = ObservableField()

        restaurantWebService.getRestaurant(id)
            .subscribeOn(io.reactivex.schedulers.Schedulers.io())
            .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
            .subscribe(
                { restaurant ->
                    returnRestaurant.set(restaurant)
                },
                { _ ->
                    returnRestaurant.set(null)
                }
            )

        return returnRestaurant
    }

    fun setCurrentRestaurant(restaurant: Restaurant) {
        Log.d("RestaurantRepository", "setCurrentRestaurant: ${restaurant.name}")
        currentRestaurant.set(restaurant)
    }

    fun setCurrentRestaurant(id: Int) {
        currentRestaurant.set(getRestaurant(id).get())
    }
}