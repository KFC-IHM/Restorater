package com.kfc.restorater

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager

class RestoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Login Notification",
            NotificationManager.IMPORTANCE_HIGH
        )
        getSystemService(NotificationManager::class.java)
            .createNotificationChannel(channel)
    }

    companion object {
        const val CHANNEL_ID = "login_notification_channel"
    }
}