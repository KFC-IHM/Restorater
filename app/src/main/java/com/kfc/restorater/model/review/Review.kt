package com.kfc.restorater.model.review

data class Review(
    val id: Int? = null,
    val title: String,
    val description: String,
    val rating: Int,
    val restaurant: Int,
    val author: Int,
)
