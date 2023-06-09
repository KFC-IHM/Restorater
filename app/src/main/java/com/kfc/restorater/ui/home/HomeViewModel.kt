package com.kfc.restorater.ui.home

import android.util.Log
import androidx.databinding.BaseObservable
import com.kfc.restorater.data.RestaurantRepository

class HomeViewModel(val restaurantRepository: RestaurantRepository) : BaseObservable() {
    fun search(text: String) {
        Log.d("HomeViewModel", "search: $text")
        restaurantRepository.currentSearch.set(text)
    }
}