package com.kfc.restorater.model.restaurant

data class Restaurant(
    val id: Int? = null,
    val name: String,
    val description: String,
    val image: String? = null,
    val owner: Int,
)

