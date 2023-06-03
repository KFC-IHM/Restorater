package com.kfc.restorater.recyclers.restaurant

import android.os.Bundle
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
class RestaurantFragment : Fragment() {

    private lateinit var restorantViewModel: RestaurantViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_restorant_list, container, false)

        restorantViewModel = ViewModelFactory.create(RestaurantViewModel::class.java)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)

                adapter = RestaurantRecyclerViewAdapter(restorantViewModel.restaurantRepository.restaurants.get()?: emptyList())

                // When the restaurant data is updated, update the adapter (restaurants)
                restorantViewModel.restaurantRepository.restaurants.addOnPropertyChangedCallback(object :
                    androidx.databinding.Observable.OnPropertyChangedCallback() {
                    override fun onPropertyChanged(
                        sender: androidx.databinding.Observable?,
                        propertyId: Int
                    ) {
                        restorantViewModel.restaurantRepository.restaurants.get()?.let {
                            adapter = RestaurantRecyclerViewAdapter(it)
                        }
                    }
                })
            }
        }


        return view
    }
}