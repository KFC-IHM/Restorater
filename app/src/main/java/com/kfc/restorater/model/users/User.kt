package com.kfc.restorater.data.users

import com.kfc.restorater.data.comment.Comment
import com.kfc.restorater.data.restaurant.Restaurant
import com.kfc.restorater.data.review.Review

// use django model
data class User(
    val id: Int? = null,
    val username: String,
    val email: String,
    val first_name: String,
    val last_name: String,
    val is_staff: Boolean,
    val is_active: Boolean,
    val date_joined: String,
    val last_login: String,
    val groups: List<String>,
    val user_permissions: List<String>,
    val is_moderator: Boolean,
    val is_restaurateur: Boolean,
    val is_customer: Boolean,
    val review_set: List<Review>,
    val restaurant_set: List<Restaurant>,
    val comment_set: List<Comment>,
)
