package com.karrar.betterlife.ui

import android.os.Bundle
import com.github.aachartmodel.aainfographics.aachartcreator.*
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.karrar.betterlife.R
import com.karrar.betterlife.databinding.ActivityMainBinding
import com.karrar.betterlife.ui.home.HomeViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}