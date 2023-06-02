package com.kfc.restorater.recyclers.restorant

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kfc.restorater.databinding.FragmentRestorantBinding
import com.kfc.restorater.model.restaurant.Restaurant

class RestorantRecyclerViewAdapter(private val restaurants: List<Restaurant>) :
    RecyclerView.Adapter<RestorantRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestorantRecyclerViewAdapter.ViewHolder {
        return ViewHolder(FragmentRestorantBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RestorantRecyclerViewAdapter.ViewHolder, position: Int) {
        Log.d("RestorantRecyclerViewAdapter", "onBindViewHolder: " + restaurants.get(position).name)
        holder.restaurantName.text = restaurants.get(position).name
        holder.restaurantRating.text = restaurants.get(position).rating().toString()
    }

    override fun getItemCount(): Int = restaurants.size

    inner class ViewHolder(binding: FragmentRestorantBinding) : RecyclerView.ViewHolder(binding.root) {
        val restaurantName = binding.restaurantName
        val restaurantRating = binding.restaurantRating

        override fun toString(): String {
            return super.toString() + " '" + restaurantName + " <" + restaurantRating + "stars>'"
        }
    }

}