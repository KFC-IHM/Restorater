package com.kfc.restorater.ui.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.kfc.restorater.R
import com.kfc.restorater.model.restaurant.Restaurant
import com.kfc.restorater.repo.RetrofitWebServiceFactory
import com.kfc.restorater.repo.api.RestaurantRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class LocationFragment : Fragment() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val restaurantRepo: RestaurantRepo =
        RetrofitWebServiceFactory.build(
            RestaurantRepo::class.java
        )

    private var currentRestaurant: Restaurant? = null

    fun getLocation(callback: (LatLng) -> Unit) {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
            .addOnSuccessListener { location ->
                val currentLatLng = location
                    ?.let { LatLng(it.latitude, it.longitude) }
                    ?: LatLng(0.0, 0.0)

                callback(currentLatLng)
            }
    }

    fun distanceBetween(
        position: LatLng,
        restaurant: Restaurant
    ): Float {
        val distance = FloatArray(1)
        Location.distanceBetween(
            position.latitude,
            position.longitude,
            restaurant.latitude,
            restaurant.longitude,
            distance
        )
        return distance[0]
    }

    private fun getClosestRestaurant(currentLatLng: LatLng, callback: (List<Restaurant>) -> Unit) {
        restaurantRepo.getRestaurants()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { restaurants ->
                val closestRestaurants = restaurants
                    .sortedBy { distanceBetween(currentLatLng, it) }
                    .take(10)

                callback(closestRestaurants)
            }
    }

    @SuppressLint("MissingPermission")
    private val callback = OnMapReadyCallback { googleMap ->
        Log.e("LocationFragment", "callback")

        getLocation { currentLatLng: LatLng ->

            val pos = currentRestaurant?.toLatLng() ?: currentLatLng
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 13f))

            googleMap.addMarker(
                com.google.android.gms.maps.model.MarkerOptions()
                    .position(currentLatLng)
                    .title("You are here")
                    // blue marker
                    .icon(
                        com.google.android.gms.maps.model.BitmapDescriptorFactory.defaultMarker(
                            com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_AZURE
                        )
                    )
            )

            getClosestRestaurant(pos) { restaurants ->
                restaurants.forEach { restaurant ->
                    googleMap.addMarker(
                        com.google.android.gms.maps.model.MarkerOptions()
                            .position(restaurant.toLatLng())
                            .title(restaurant.name)
                    )
                }
            }
        }
    }

    private fun requestPermissions() {
        val requestMultiplePermissions =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                permissions.entries.forEach {
                    Log.d("LocationFragment", "${it.key} = ${it.value}")
                }
            }

        requestMultiplePermissions.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        requestPermissions()

        currentRestaurant = arguments?.getParcelable("restaurant")

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}