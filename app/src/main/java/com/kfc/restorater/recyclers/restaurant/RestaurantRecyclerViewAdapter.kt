package com.kfc.restorater.recyclers.restaurant

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kfc.restorater.databinding.FragmentRestaurantBinding
import com.kfc.restorater.model.restaurant.Restaurant

class RestaurantRecyclerViewAdapter(private val restaurants: List<Restaurant>) :
    RecyclerView.Adapter<RestaurantRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantRecyclerViewAdapter.ViewHolder {
        return ViewHolder(FragmentRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RestaurantRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.restaurantName.text = restaurants[position].name
        holder.restaurantRating.text = restaurants[position].rating().toString()
    }

    override fun getItemCount(): Int = restaurants.size

    inner class ViewHolder(binding: FragmentRestaurantBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        val restaurantName = binding.restaurantName
        val restaurantRating = binding.restaurantRating

        override fun toString(): String {
            return super.toString() + " '" + restaurantName + " <" + restaurantRating + "stars>'"
        }

        override fun onClick(p0: View?) {
            Log.d("RestaurantRecyclerViewAdapter", "onClick")
            TODO("Not yet implemented")
        }
    }
}