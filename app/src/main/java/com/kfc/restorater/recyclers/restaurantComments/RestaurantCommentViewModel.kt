package com.kfc.restorater.recyclers.restaurantComments

import androidx.databinding.BaseObservable
import com.kfc.restorater.data.RestaurantRepository
import com.kfc.restorater.data.ReviewRepository

class RestaurantCommentViewModel(val restaurantRepository: RestaurantRepository, val reviewRepository: ReviewRepository) : BaseObservable() {}