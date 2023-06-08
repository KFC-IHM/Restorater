package com.kfc.restorater.ui.restaurant

import android.content.Context
import android.content.Intent
import android.provider.ContactsContract
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import com.kfc.restorater.data.RestaurantRepository

class RestaurantPageViewModel(val restaurantRepository: RestaurantRepository) : BaseObservable() {

    var restaurant = ObservableField(restaurantRepository.currentRestaurant.get())
    var context: Context? = null
    var distance = ObservableField("")

    companion object {
        var intent = Intent(ContactsContract.Intents.Insert.ACTION)
    }

    fun addContact() {
        // ContactsContract permet d'ajouter un contact dans le répertoire du téléphone
        // Inser.action permet de créer un nouveau contact

        // On précise que l'on veut ajouter un contact de type "personne"
        intent.type = ContactsContract.RawContacts.CONTENT_TYPE

        // On ajoute le nom et le numéro de téléphone avec putExtra
        intent.putExtra(ContactsContract.Intents.Insert.NAME, "KFC")
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, "0142578482")

        context?.startActivity(intent)
    }

    fun getRating(): String {
        if (restaurant.get()?.rating()?.isNaN() == true) {
            return "No rating"
        }
        return restaurant.get()?.rating().toString()
    }
}