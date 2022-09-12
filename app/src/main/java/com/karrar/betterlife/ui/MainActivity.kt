package com.karrar.betterlife.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.karrar.betterlife.R
import com.karrar.betterlife.databinding.ActivityMainBinding
import com.karrar.betterlife.ui.home.HomeViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private val _viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        _binding.apply {
            viewModel = _viewModel
            lifecycleOwner = this@MainActivity
        }

        _viewModel.checkedBtnObs.observe(this){
            Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_SHORT).show()
        }


    }
}