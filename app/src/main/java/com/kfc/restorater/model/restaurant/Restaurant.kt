package com.kfc.restorater.model.restaurant

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import com.kfc.restorater.model.review.Review
import kotlinx.parcelize.Parcelize

@Parcelize
data class Restaurant(
    val id: Int? = null,
    val name: String,
    val description: String,
    val image: String? = null,
    val owner: Int,
    val review_set: List<Review>,
    val latitude: Double,
    val longitude: Double,
) : Parcelable {

    fun rating(): Float {
        return review_set.map { it.rating }.average().toFloat()
    }

    fun toLatLng(): LatLng {
        return LatLng(latitude, longitude)
    }
}

