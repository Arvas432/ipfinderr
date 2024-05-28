package com.example.ipfinderr.ui.root

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.ipfinderr.R
import com.example.ipfinderr.databinding.ActivityRootBinding

class RootActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRootBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container_view) as NavHostFragment
        val navController = navHostFragment.navController
//        val options = NavOptions.Builder()
//            .setLaunchSingleTop(true)
//            .setEnterAnim(R.anim.slide_in_left)
//            .setExitAnim(R.anim.slide_out_right)
//            .setPopEnterAnim(R.anim.slide_in_right)
//            .setPopExitAnim(R.anim.slide_out_left)
//            .setPopUpTo(navController.graph.startDestinationId, false)
//            .build()
        binding.bottomNavigationView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.mapsHostFragment, R.id.additionalInfoFragment -> {
                    binding.bottomNavigationView.isVisible = false
                }
                else -> {
                    binding.bottomNavigationView.isVisible = true
                }
            }
        }
    }
}