package com.kfc.restorater

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
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