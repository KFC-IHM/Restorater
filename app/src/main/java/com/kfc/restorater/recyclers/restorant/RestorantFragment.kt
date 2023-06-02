package com.kfc.restorater.recyclers.restorant

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
class RestorantFragment : Fragment() {

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

                // When the restaurant data is updated, update the adapter (restaurants)
                restorantViewModel.restaurants.addOnPropertyChangedCallback(object :
                    androidx.databinding.Observable.OnPropertyChangedCallback() {
                    override fun onPropertyChanged(
                        sender: androidx.databinding.Observable?,
                        propertyId: Int
                    ) {
                        restorantViewModel.restaurants.get()?.let {
                            adapter = RestorantRecyclerViewAdapter(it)
                        }
                    }
                })
            }
        }


        return view
    }
}