package com.example.moviesk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.moviesk.data.DataSource
import com.example.moviesk.domain.RepoImpl
import com.example.moviesk.ui.viewmodel.MainViewModel
import com.example.moviesk.ui.viewmodel.VMFactory

class MainActivity : AppCompatActivity() {

     private lateinit var navController: NavController

     override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          setContentView(R.layout.activity_main)

          // Because we're creating the NavHostFragment using FragmentContainerView, we must
          // retrieve the NavController directly from the NavHostFragment instead
          val navHostFragment =
               supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
          navController = navHostFragment.navController

          NavigationUI.setupActionBarWithNavController(this, navController)

     }

     override fun onSupportNavigateUp(): Boolean {
          //navController = this.findNavController(R.id.nav_host_fragment)
          return navController.navigateUp()
     }

     /*override fun onNavigateUp(): Boolean {
          navController = this.findNavController(R.id.nav_host_fragment)
          return navController.navigateUp()
     }*/
}