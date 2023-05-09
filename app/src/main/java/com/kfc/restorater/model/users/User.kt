package com.kfc.restorater.model.users

import android.os.Parcelable
import com.kfc.restorater.model.comment.Comment
import com.kfc.restorater.model.restaurant.Restaurant
import com.kfc.restorater.model.review.Review
import kotlinx.parcelize.Parcelize

@Parcelize
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
) : Parcelable
