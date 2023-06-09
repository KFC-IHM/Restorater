package com.kfc.restorater.ui.location

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.kfc.restorater.R
import com.kfc.restorater.model.restaurant.Restaurant
import com.kfc.restorater.repo.RetrofitWebServiceFactory
import com.kfc.restorater.repo.api.RestaurantApi
import com.kfc.restorater.services.LocationHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class LocationFragment : Fragment() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val restaurantApi: RestaurantApi =
        RetrofitWebServiceFactory.build(
            RestaurantApi::class.java
        )

    private var currentRestaurant: Restaurant? = null

    private fun getClosestRestaurant(currentLatLng: LatLng, callback: (List<Restaurant>) -> Unit) {
        restaurantApi.getRestaurants()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { restaurants ->
                val closestRestaurants = restaurants
                    .sortedBy { LocationHelper.distanceBetween(currentLatLng, it) }
                    .take(10)

                callback(closestRestaurants)
            }
    }

    @SuppressLint("MissingPermission")
    private val callback = OnMapReadyCallback { googleMap ->
        Log.e("LocationFragment", "callback")

        LocationHelper.getLocation(requireActivity()) { currentLatLng: LatLng ->

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
        LocationHelper.requestLocationAccess(requireActivity())

        currentRestaurant = arguments?.getParcelable("restaurant")

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}