package com.kfc.restorater.recyclers

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import com.kfc.restorater.databinding.FragmentCommentBinding
import com.kfc.restorater.model.review.Review


class CommentRecyclerViewAdapter(private val reviews: List<Review>)
    : RecyclerView.Adapter<CommentRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FragmentCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.reviewTitle.text = reviews.get(position).title
        holder.reviewRating.text = reviews.get(position).rating.toString()
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