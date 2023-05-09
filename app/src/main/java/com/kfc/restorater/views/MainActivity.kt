import androidx.appcompat.app.AppCompatActivity
import com.kfc.restorater.viewmodels.RestaurantViewModel

class MainActivity : AppCompatActivity() {


    val restaurantViewModel = RestaurantViewModel()

    restaurantViewModel.getRestaurants().observe(this, Observer<List<RestaurantModel>> { restaurants ->
        // update UI
    })
}