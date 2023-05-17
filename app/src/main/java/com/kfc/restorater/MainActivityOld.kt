package com.kfc.restorater

import android.os.Bundle
import android.view.Menu
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.kfc.restorater.repo.RetrofitWebServiceGenerator
import com.kfc.restorater.repo.api.RestaurantRepo

class MainActivityOld : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.old_activity_main)

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
            RetrofitWebServiceGenerator.createService(RestaurantRepo::class.java)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        //menuInflater.inflate(R.menu.menu_main, menu) TODO: Uncomment this line
        return true
    }


}