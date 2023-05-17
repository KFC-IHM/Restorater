package com.kfc.restorater.model.users

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class JWTModel(
    val refresh: String,
    val access: String,
) : Parcelable