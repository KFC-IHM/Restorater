package com.kfc.restorater

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listMenu = findViewById<ImageButton>(R.id.list_menu)
        listMenu.setOnClickListener {
            setContentView(R.layout.activity_main)
        }

        val mapMenu = findViewById<ImageButton>(R.id.map_menu)
        mapMenu.setOnClickListener {
            setContentView(R.layout.activity_map)
        }

        val userMenu = findViewById<ImageButton>(R.id.user_menu)
        userMenu.setOnClickListener {
            setContentView(R.layout.activity_user)
        }
    }
}