import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.kfc.restorater.viewmodels.RestaurantViewModel

class MainActivity : AppCompatActivity() {


    val restaurantViewModel = RestaurantViewModel()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        // On restaurant update, update the view
        restaurantViewModel.getRestaurants()?.subscribe { restaurants ->
            // Update view
        }

    }
}