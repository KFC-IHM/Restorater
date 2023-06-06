package com.kfc.restorater.recyclers.restaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.kfc.restorater.R
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

        holder.itemView.setOnClickListener {view ->
            view.findNavController().navigate(R.id.navigation_restaurant)
        }
    }

    override fun getItemCount(): Int = restaurants.size

    inner class ViewHolder(binding: FragmentRestaurantBinding) : RecyclerView.ViewHolder(binding.root) {
        val restaurantName = binding.restaurantName
        val restaurantRating = binding.restaurantRating

        override fun toString(): String {
            return super.toString() + " '" + restaurantName + " <" + restaurantRating + "stars>'"
        }
    }
}