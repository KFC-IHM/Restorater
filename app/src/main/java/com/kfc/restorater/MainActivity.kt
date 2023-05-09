package com.kfc.restorater

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import com.kfc.restorater.data.restaurant.Restaurant
import com.kfc.restorater.databinding.ActivityMainBinding
import com.kfc.restorater.repository.remote.restaurants.RetrofitRestaurantApi
import com.kfc.restorater.repository.remote.restaurants.RetrofitWebServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.old_activity_main)

        sendNotification(
            "Connexion réussie",
            "Vous êtes bien connecté à Restorater !",
            RestoApplication.CHANNEL_ID,
            NotificationCompat.PRIORITY_DEFAULT
        )

        val listMenu = findViewById<ImageButton>(R.id.list_menu)
        listMenu.setOnClickListener {
            setContentView(R.layout.old_activity_main)
        }

        val mapMenu = findViewById<ImageButton>(R.id.map_menu)
        mapMenu.setOnClickListener {
            //TODO: Change to map activity
            // setContentView(R.layout.activity_map)
        }

        val userMenu = findViewById<ImageButton>(R.id.user_menu)
        userMenu.setOnClickListener {
            //TODO: Change to user activity
            // setContentView(R.layout.activity_user)
        }

        val webService =
            RetrofitWebServiceGenerator().createService(RetrofitRestaurantApi::class.java)
//        // log the result of the call to the web service
//        webService.getRestaurants().enqueue(object : Callback<List<Restaurant>> {
//            override fun onResponse(
//                call: Call<List<Restaurant>>,
//                response: Response<List<Restaurant>>
//            ) {
//                Log.d("MainActivity", "onResponse: ${response.body()}")
//            }
//
//            override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
//                Log.d("MainActivity", "onFailure: ${t.message}")
//            }
//        })

        webService.getRestaurant(1).enqueue(object : Callback<Restaurant> {
            override fun onResponse(call: Call<Restaurant>, response: Response<Restaurant>) {
                Log.d("MainActivity", "onResponse: ${response.body()}")
            }

            override fun onFailure(call: Call<Restaurant>, t: Throwable) {
                Log.d("MainActivity", "onFailure: ${t.message}")
            }
        })


        val image = assets.open("test.png").readBytes()
        webService.createRestaurant(
            Restaurant(
                name = "Test Restaurant",
                description = "Test Description",
                owner = 1,
            )
        ).enqueue(object : Callback<Restaurant> {
            override fun onResponse(call: Call<Restaurant>, response: Response<Restaurant>) {
                Log.d("MainActivity", "onResponse: ${response.message()}")
            }

            override fun onFailure(call: Call<Restaurant>, t: Throwable) {
                Log.d("MainActivity", "onFailure: ${t.message} ${t.cause}")
            }
        }
        )

//        val authWebService =
//            RetrofitWebServiceGenerator().createService(RetrofitAuthApi::class.java)
//        authWebService.login(
//            Credentials(
//                username = "admin",
//                password = "password"
//            )
//        ).enqueue(
//            object : Callback<JWT> {
//                override fun onResponse(
//                    call: Call<JWT>,
//                    response: Response<JWT>
//                ) {
//                    Log.d("MainActivity", "login onResponse: ${response.body()}")
//                }
//
//                override fun onFailure(call: Call<JWT>, t: Throwable) {
//                    Log.d("MainActivity", "login onFailure: ${t.message}")
//                }
//            }
//        )


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    private fun sendNotification(title: String, message: String, channelId: String, priority: Int) {
        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(priority)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        NotificationManagerCompat.from(applicationContext).notify(0, notification.build())
    }

}