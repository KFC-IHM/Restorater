package com.kfc.restorater.ui.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kfc.restorater.databinding.FragmentRestaurantBinding
import com.kfc.restorater.factory.ViewModelFactory

class RestaurantFragment : Fragment() {

    private lateinit var restaurantViewModel: RestaurantViewModel
    private var _binding: FragmentRestaurantBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRestaurantBinding.inflate(inflater, container, false)
        restaurantViewModel = ViewModelFactory.create(RestaurantViewModel::class.java)
        binding.restaurantName.text = restaurantViewModel.restaurantRepository.currentRestaurant.get()?.name

        restaurantViewModel.restaurantRepository.currentRestaurant.addOnPropertyChangedCallback(object :
            androidx.databinding.Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: androidx.databinding.Observable?, propertyId: Int) {
                binding.restaurantName.text = restaurantViewModel.restaurantRepository.currentRestaurant.get()?.name
            }
        })

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
