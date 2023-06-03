package com.kfc.restorater.data

import androidx.databinding.ObservableField
import com.kfc.restorater.model.restaurant.Restaurant
import com.kfc.restorater.repo.RetrofitWebServiceGenerator
import com.kfc.restorater.repo.api.RestaurantRepo

class RestaurantRepository {
    var restaurants: ObservableField<List<Restaurant>> = ObservableField()

    var currentRestaurant: ObservableField<Restaurant> = ObservableField()

    private val restorantWebService: RestaurantRepo = RetrofitWebServiceGenerator.createService(
        RestaurantRepo::class.java)

    init {
        restorantWebService.getRestaurants()
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

    fun getRestaurant(id: Int) {
        restorantWebService.getRestaurant(id)
            .subscribeOn(io.reactivex.schedulers.Schedulers.io())
            .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
            .subscribe(
                { restaurant ->
                    this.currentRestaurant.set(restaurant)
                },
                { _ ->
                    this.currentRestaurant.set(null)
                }
            )
    }

    fun getCurrentRestaurant(): ObservableField<Restaurant> {
        return currentRestaurant
    }

}