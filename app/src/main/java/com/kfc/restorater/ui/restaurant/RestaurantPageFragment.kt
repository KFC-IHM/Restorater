package com.kfc.restorater.ui.restaurant

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kfc.restorater.databinding.FragmentRestaurantPageBinding
import com.kfc.restorater.factory.ViewModelFactory

class RestaurantPageFragment : Fragment() {

    private lateinit var restaurantViewModel: RestaurantPageViewModel
    private var _binding: FragmentRestaurantPageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("RestaurantPageFragment", "onCreateView")

        _binding = FragmentRestaurantPageBinding.inflate(inflater, container, false)
        restaurantViewModel = ViewModelFactory.create(RestaurantPageViewModel::class.java)
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
