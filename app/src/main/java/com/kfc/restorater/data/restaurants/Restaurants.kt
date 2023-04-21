package com.kfc.restorater.data

data class Restaurant(
    val id: Int? = null,
    val name: String,
    val description: String,
    val image: String,
    val owner: Int,
)

