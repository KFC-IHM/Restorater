package com.kfc.restorater.recyclers.restaurant

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.kfc.restorater.R
import com.kfc.restorater.data.RestaurantRepository
import com.kfc.restorater.databinding.FragmentRestaurantBinding
import com.kfc.restorater.model.restaurant.Restaurant

class RestaurantRecyclerViewAdapter(private val restaurantRepository: RestaurantRepository) :
    RecyclerView.Adapter<RestaurantRecyclerViewAdapter.ViewHolder>() {

    var restaurants: List<Restaurant> = restaurantRepository.restaurants.get() ?: emptyList()

    init {
        // When the restaurant data is updated, update the adapter (restaurants)
        restaurantRepository.restaurants.addOnPropertyChangedCallback(object :
            androidx.databinding.Observable.OnPropertyChangedCallback() {
            @SuppressLint("NotifyDataSetChanged")
            override fun onPropertyChanged(
                sender: androidx.databinding.Observable?,
                propertyId: Int
            ) {
                restaurantRepository.restaurants.get()?.let {
                    restaurants = it
                    notifyDataSetChanged() // Notify the adapter about the data change
                }
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantRecyclerViewAdapter.ViewHolder {
        return ViewHolder(FragmentRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RestaurantRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.restaurantName.text = restaurants[position].name
        holder.restaurantRating.text = restaurants[position].rating().toString()
        // if NaN hide the rating
        if (restaurants[position].rating().isNaN()) {
            holder.restaurantRating.text = "No rating"
        }

        holder.itemView.setOnClickListener { view ->
            restaurantRepository.setCurrentRestaurant(restaurants[position])
            Log.d("RestaurantRecyclerViewAdapter", "Current restaurant: ${restaurantRepository.currentRestaurant.get()?.name}")
            view.findNavController().navigate(R.id.action_navigation_home_to_navigation_restaurant)
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
