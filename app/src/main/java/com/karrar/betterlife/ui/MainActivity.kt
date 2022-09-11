package com.karrar.betterlife.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.aachartmodel.aainfographics.aachartcreator.*
import com.karrar.betterlife.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // just to test
        val aaCharts = Charts(
            dataOfHabits = arrayOf(7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2),
            dataOfPlans = arrayOf(3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0))

        val aaChartView = findViewById<AAChartView>(R.id.aa_chart_view)
        aaChartView.aa_drawChartWithChartModel(aaCharts.charts)
    }
}