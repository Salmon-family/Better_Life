package com.karrar.betterlife.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.karrar.betterlife.R
import com.karrar.betterlife.databinding.ActivityMainBinding
import com.karrar.betterlife.util.workManager.NotificationScheduler


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        NotificationScheduler(this).initialDelay()

    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.nav_host_fragment)
        binding.bottomNavigationBar.setupWithNavController(navController)
    }

    override fun onResume() {
        super.onResume()
        setupNavigation()
    }
}