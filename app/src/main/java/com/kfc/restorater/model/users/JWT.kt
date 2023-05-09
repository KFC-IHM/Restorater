package com.kfc.restorater.model.users

data class JWT(
    val refresh: String,
    val access: String,
)