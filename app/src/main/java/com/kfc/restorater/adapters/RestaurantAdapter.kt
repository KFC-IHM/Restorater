package com.kfc.restorater.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kfc.restorater.R
import com.kfc.restorater.model.restaurant.Restaurant

class RestaurantAdapter(): RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {
    private var restaurants: List<Restaurant> = emptyList()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.restaurant_item, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun getItemCount(): Int {
        return restaurants.size
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.bind(restaurant)
    }

    inner class RestaurantViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.restaurant_name)
        private val distance: TextView = itemView.findViewById(R.id.restaurant_distance)
        private val stars: TextView = itemView.findViewById(R.id.restaurant_rating)

        fun bind(restaurant: Restaurant) {
            name.text = restaurant.name
            distance.text = "2" // TODO: Calculate distance
            stars.text = restaurant.rating().toString()
        }
    }
}