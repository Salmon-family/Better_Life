package com.karrar.betterlife.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.karrar.betterlife.R
import com.karrar.betterlife.util.workManager.NotificationScheduler


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)
        NotificationScheduler(this).initialDelay()
    }
}