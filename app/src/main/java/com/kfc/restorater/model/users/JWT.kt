package com.kfc.restorater.data.users

data class JWT(
    val refresh: String,
    val access: String,
)