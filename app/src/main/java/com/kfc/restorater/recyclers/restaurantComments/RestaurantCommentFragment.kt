package com.kfc.restorater.recyclers.restaurantComments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kfc.restorater.R
import com.kfc.restorater.factory.ViewModelFactory

/**
 * A fragment representing a list of Items.
 */
class RestaurantCommentFragment : Fragment() {

    private lateinit var restaurantCommentViewModel: RestaurantCommentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_restaurant_comment_list, container, false)
        Log.d("RestaurantCommentFragment", "onCreateView")

        restaurantCommentViewModel = ViewModelFactory.create(RestaurantCommentViewModel::class.java)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)

                adapter = RestaurantCommentRecyclerViewAdapter(restaurantCommentViewModel.restaurantRepository)
            }
        }
        return view
    }
}