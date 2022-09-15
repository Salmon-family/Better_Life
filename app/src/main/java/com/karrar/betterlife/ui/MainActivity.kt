package com.karrar.betterlife.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.karrar.betterlife.R
import com.karrar.betterlife.ui.workManager.NotificationScheduler


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NotificationScheduler(this).initialDelay()
    }
}