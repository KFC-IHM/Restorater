package com.kfc.restorater.ui.restaurant

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kfc.restorater.databinding.FragmentRestaurantPageBinding
import com.kfc.restorater.factory.ViewModelFactory
import com.kfc.restorater.ui.location.LocationFragment

class RestaurantPageFragment : Fragment() {

    private lateinit var restaurantViewModel: RestaurantPageViewModel
    private var _binding: FragmentRestaurantPageBinding? = null
    private lateinit var map: LocationFragment

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    init {
        map = LocationFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("RestaurantPageFragment", "onCreateView")

        _binding = FragmentRestaurantPageBinding.inflate(inflater, container, false)

        restaurantViewModel = ViewModelFactory.create(RestaurantPageViewModel::class.java)

        binding.restaurantDriveTo.setOnClickListener { navigateGMaps() }

        if (container != null) {
            restaurantViewModel.context = container.context
        }

        map.arguments = Bundle().apply {
            putParcelable(
                "restaurant",
                restaurantViewModel.restaurantRepository.currentRestaurant.get()
            )
        }

        if (childFragmentManager.findFragmentById(com.kfc.restorater.R.id.restaurant_map) == null)
            childFragmentManager.beginTransaction().add(
                com.kfc.restorater.R.id.restaurant_map,
                map
            ).commit()

        map =
            childFragmentManager.findFragmentById(com.kfc.restorater.R.id.restaurant_map) as LocationFragment

        map.getLocation { location ->
            val restaurant = restaurantViewModel.restaurantRepository.currentRestaurant.get()
                ?: return@getLocation

            val distance = map.distanceBetween(location, restaurant)
            val distanceString = String.format("%.2f", distance / 1000)
            restaurantViewModel.distance.set(distanceString)
        }

        binding.viewmodel = restaurantViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun navigateGMaps() {
        Log.d("RestaurantPageFragment", "navigateGMaps")
        val gmmIntentUri =
            Uri.parse("google.navigation:q=" + restaurantViewModel.restaurantRepository.currentRestaurant.get()?.latitude + "," + restaurantViewModel.restaurantRepository.currentRestaurant.get()?.longitude)
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }
}
