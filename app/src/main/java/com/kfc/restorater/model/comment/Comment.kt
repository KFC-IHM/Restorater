package com.kfc.restorater.model.comment

data class Comment(
    val id: Int? = null,
    val description: String,
    val review: Int,
    val author: Int,
)
