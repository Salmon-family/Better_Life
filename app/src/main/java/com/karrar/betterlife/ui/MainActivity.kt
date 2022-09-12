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
    private lateinit var _binding: ActivityMainBinding
    private val _viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // just to test
        val aaCharts = Charts(arrayOf(7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2))

        val aaChartView = findViewById<AAChartView>(R.id.aa_chart_view)
        aaChartView.aa_drawChartWithChartModel(aaCharts.drawCharts())

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