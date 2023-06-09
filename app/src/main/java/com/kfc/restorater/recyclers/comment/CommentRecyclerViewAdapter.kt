package com.kfc.restorater.recyclers.comment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.kfc.restorater.R
import com.kfc.restorater.data.LoginRepository
import com.kfc.restorater.data.ReviewRepository
import com.kfc.restorater.databinding.FragmentCommentBinding
import com.kfc.restorater.model.review.Review

class CommentRecyclerViewAdapter(private val reviews: List<Review>,private val loginRepository: LoginRepository, private val reviewRepository: ReviewRepository)
    : RecyclerView.Adapter<CommentRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FragmentCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.reviewTitle.text = reviews[position].title
        holder.reviewRating.text = reviews[position].rating.toString()
        // if NaN hide the rating
        if (reviews[position].rating == 0) {
            holder.reviewRating.text = "No rating"
        }
        holder.itemView.setOnClickListener { view ->
            reviewRepository.setCurrentReview(reviews[position])
            Log.d("RestaurantRecyclerViewAdapter", "Current restaurant: ${reviewRepository.currentReview.get()?.title}")
            view.findNavController().navigate(R.id.action_navigation_user_to_navigation_comment_page)
        }
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