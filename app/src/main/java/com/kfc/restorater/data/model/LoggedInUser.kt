package com.kfc.restorater.data.model

import com.kfc.restorater.model.users.JWTModel

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    val userId: Int,
    val jwt: JWTModel
)