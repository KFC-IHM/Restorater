package com.kfc.restorater.data

import android.util.Log
import androidx.databinding.ObservableField
import com.kfc.restorater.model.restaurant.Restaurant
import com.kfc.restorater.repo.RetrofitWebServiceFactory
import com.kfc.restorater.repo.api.RestaurantApi

class RestaurantRepository {
    var allRestaurants: ObservableField<List<Restaurant>> = ObservableField()

    var restaurants: ObservableField<List<Restaurant>> = ObservableField()

    var currentRestaurant: ObservableField<Restaurant> = ObservableField()

    var currentSearch = ObservableField<String>("")

    private val restaurantWebService: RestaurantApi = RetrofitWebServiceFactory.build(
        RestaurantApi::class.java)

    init {
        restaurantWebService.getRestaurants()
            .subscribeOn(io.reactivex.schedulers.Schedulers.io())
            .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
            .subscribe(
                { restaurants ->
                    this.allRestaurants.set(restaurants)
                    this.restaurants.set(restaurants)
                },
                { _ ->
                    this.allRestaurants.set(null)
                    this.restaurants.set(null)
                }
            )

        currentSearch.addOnPropertyChangedCallback(object : androidx.databinding.Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: androidx.databinding.Observable?, propertyId: Int) {
                Log.d("RestaurantRepository", "onPropertyChanged: ${currentSearch.get()}")
                search(currentSearch.get()!!)
            }
        })
    }

    private fun search(text: String) {
        restaurants.set(allRestaurants.get()!!.filter { restaurant ->
            restaurant.name.contains(text, true)
        })
    }

    fun setCurrentRestaurant(restaurant: Restaurant) {
        Log.d("RestaurantRepository", "setCurrentRestaurant: ${restaurant.name}")
        currentRestaurant.set(restaurant)
    }
}