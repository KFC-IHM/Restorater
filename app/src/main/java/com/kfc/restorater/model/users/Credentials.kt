package com.kfc.restorater.model.users

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Credentials(
    val username: String,
    val password: String,
) : Parcelable
