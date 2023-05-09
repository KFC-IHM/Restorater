package com.kfc.restorater.model.review

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Review(
    val id: Int? = null,
    val title: String,
    val description: String,
    val rating: Int,
    val restaurant: Int,
    val author: Int,
) : Parcelable
