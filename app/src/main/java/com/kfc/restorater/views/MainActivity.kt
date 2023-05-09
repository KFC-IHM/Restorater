import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.kfc.restorater.R
import com.kfc.restorater.RestoApplication
import com.kfc.restorater.viewmodels.RestaurantViewModel

class MainActivity : AppCompatActivity() {
    val restaurantViewModel = RestaurantViewModel()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        // Send notification on start
        sendNotification(
            "Connexion réussie",
            "Vous êtes bien connecté à Restorater !",
            RestoApplication.CHANNEL_ID,
            NotificationCompat.PRIORITY_DEFAULT
        )

        // On restaurant update, update the view
        restaurantViewModel.getRestaurants()?.subscribe { restaurants ->
            // Update view
        }
    }

    private fun sendNotification(title: String, message: String, channelId: String, priority: Int) {
        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(priority)

        NotificationManagerCompat.from(applicationContext).notify(0, notification.build())
    }
}