package com.kfc.restorater.recyclers.restaurantComments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kfc.restorater.data.RestaurantRepository
import com.kfc.restorater.databinding.FragmentCommentBinding


class RestaurantCommentRecyclerViewAdapter(private val restaurantRepository: RestaurantRepository) : RecyclerView.Adapter<RestaurantCommentRecyclerViewAdapter.ViewHolder>() {

    var reviews = restaurantRepository.currentRestaurant.get()?.review_set ?: emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FragmentCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.reviewTitle.text = reviews[position].title
        holder.reviewRating.text = reviews[position].rating.toString()
    }

    override fun getItemCount(): Int = reviews.size

    inner class ViewHolder(binding: FragmentCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        val reviewRating = binding.commentRating
        val reviewTitle = binding.commentTitle

        override fun toString(): String {
            return super.toString() + " '" + reviewTitle + " <" + reviewRating + "stars>'"
        }
    }

}