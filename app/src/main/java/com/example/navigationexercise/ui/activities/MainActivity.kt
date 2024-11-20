package com.example.navigationexercise.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.navigationexercise.R
import com.example.navigationexercise.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Navigation UI İle kullanımı
        // Top app bar bağlama
        // Navigation Drawer bağlama
        // hepsinin birleşimi idler önemli
        val navHostController = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostController.navController

       // setOf ile top level fragmentları tanımladık böylede istediğimiz fragmentlar gelince menü cubugu
        // görünmeye devam eder geri dön iconu cıkmak bunu istediğimiz fragmentlarda kullanıyourz böylede
        //ordan da manuyu acıp kapatabiliriz twitter örnek olarak verilevilirz
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.homeFragment,
            R.id.secondFragment
        ),binding.drawerLayout)




        binding.topAppBar.setupWithNavController(navController, appBarConfiguration)


        //bottom navigation bağlama
        binding.bottomNavigation.setupWithNavController(navController)


        //bottom navigaiton ile ilgili işlem olunca yapmak istediğiniz aksiyonları yazabilirsin
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.secondFragment) {
              /*  binding.topAppBar.visibility = View.GONE */
                binding.bottomNavigation.visibility = View.GONE
            } else {
               /* binding.topAppBar.visibility = View.VISIBLE */
                binding.bottomNavigation.visibility = View.VISIBLE
            }
        }
    }
}