package com.kfc.restorater.ui.restaurant

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.provider.ContactsContract
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import com.kfc.restorater.R
import com.kfc.restorater.data.RestaurantRepository

class RestaurantPageViewModel(val restaurantRepository: RestaurantRepository) : BaseObservable() {

    var restaurant = ObservableField(restaurantRepository.currentRestaurant.get())
    var context: Context? = null

    companion object {
        var intent = Intent(ContactsContract.Intents.Insert.ACTION)
        const val CHANNEL_ID = "login_notification_channel"
    }

    fun addContact() {
        sendNotif()

        intent.type = ContactsContract.RawContacts.CONTENT_TYPE


        intent.putExtra(ContactsContract.Intents.Insert.NAME, "KFC")
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, "0142578482")

        context?.startActivity(intent)
    }

    private fun sendNotif() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Login Notification",
            NotificationManager.IMPORTANCE_HIGH
        )

        val notification = Notification.Builder(context, CHANNEL_ID)
            .setContentTitle("Contact créé !")
            .setContentText("Confirmez l'ajout dans vos contacts")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()

        val notificationManager = context?.getSystemService(NotificationManager::class.java)
        notificationManager?.createNotificationChannel(channel)
        notificationManager?.notify(0, notification)
    }

    fun setContext2(context: Context) {
        this.context = context
    }
}