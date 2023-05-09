package com.kfc.restorater

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}