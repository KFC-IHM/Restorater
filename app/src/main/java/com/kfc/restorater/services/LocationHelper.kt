package com.kfc.restorater.services

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import com.kfc.restorater.model.restaurant.Restaurant

class LocationHelper {
    companion object {
        fun requestLocationAccess(activity: Activity) {
            val permission = ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION,
            )

            if (permission != PackageManager.PERMISSION_GRANTED) {
                Log.i("LocationServices", "Permission to record denied")
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                    ),
                    1
                )
            }

        }

        @SuppressLint("MissingPermission")  // We check for permission above
        fun getLocation(activity: Activity, callback: (LatLng) -> Unit) {
            requestLocationAccess(activity)

            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)


            fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
                .addOnSuccessListener { location ->
                    val currentLatLng = location
                        ?.let { LatLng(it.latitude, it.longitude) }
                        ?: LatLng(0.0, 0.0)

                    callback(currentLatLng)
                }
        }

        fun distanceBetween(position: LatLng, restaurant: Restaurant): Float {
            val distance = FloatArray(1)
            android.location.Location.distanceBetween(
                position.latitude,
                position.longitude,
                restaurant.latitude,
                restaurant.longitude,
                distance
            )
            return distance[0]
        }
    }
}