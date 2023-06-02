package com.kfc.restorater.recyclers.restorant

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import com.kfc.restorater.model.restaurant.Restaurant
import com.kfc.restorater.repo.RetrofitWebServiceGenerator
import com.kfc.restorater.repo.api.RestaurantRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RestaurantViewModel() : BaseObservable() {
    var restaurants: ObservableField<List<Restaurant>> = ObservableField()

    init {
        RetrofitWebServiceGenerator
            .createService(RestaurantRepo::class.java)
            .getRestaurants()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { restaurants ->
                this.restaurants.set(restaurants)
            }
    }
}