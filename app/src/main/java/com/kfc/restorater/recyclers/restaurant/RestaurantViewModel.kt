package com.kfc.restorater.recyclers.restaurant

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import com.kfc.restorater.model.restaurant.Restaurant
import com.kfc.restorater.repo.RetrofitWebServiceGenerator
import com.kfc.restorater.repo.api.RestaurantRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RestaurantViewModel() : BaseObservable() {
    var restaurants: ObservableField<List<Restaurant>> = ObservableField()

    private val restorantWebService: RestaurantRepo = RetrofitWebServiceGenerator.createService(RestaurantRepo::class.java)

    init {
        restorantWebService.getRestaurants()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { restaurants ->
                    this.restaurants.set(restaurants)
                },
                { _ ->
                    this.restaurants.set(null)
                }
            )
    }
}