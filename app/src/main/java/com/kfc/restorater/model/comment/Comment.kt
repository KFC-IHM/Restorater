package com.kfc.restorater.model.comment

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comment(
    val id: Int? = null,
    val description: String,
    val review: Int,
    val author: Int,
) : Parcelable
