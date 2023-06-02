package com.kfc.restorater.recyclers.restorant

import androidx.databinding.BaseObservable
import com.kfc.restorater.model.restaurant.Restaurant
import com.kfc.restorater.repo.RetrofitWebServiceGenerator
import com.kfc.restorater.repo.api.RestaurantRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RestaurantViewModel() : BaseObservable() {
    var restaurants: List<Restaurant> = ArrayList()

    init {
        RetrofitWebServiceGenerator
            .createService(RestaurantRepo::class.java)
            .getRestaurants()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { restaurants ->
                    this.restaurants = restaurants
                },
                { _ ->
                    this.restaurants = ArrayList()
                }
            )
    }
}