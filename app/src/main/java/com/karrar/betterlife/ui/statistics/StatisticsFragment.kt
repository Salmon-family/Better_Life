package com.karrar.betterlife.ui.statistics

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.karrar.betterlife.R
import com.karrar.betterlife.databinding.FragmentStatisticsBinding
import com.karrar.betterlife.ui.Charts
import com.karrar.betterlife.ui.base.BaseFragment

class StatisticsFragment : BaseFragment<FragmentStatisticsBinding, StatisticsViewModel>() {
    override val layoutIdFragment = R.layout.fragment_statistics
    override val viewModelClass = StatisticsViewModel::class.java

    override fun setup() {
        chartsWeek()
        chartsMonth()
    }


    private fun chartsWeek() {
        viewModel.isSelectedWeek.observe(this) {
            if (it) {
                charts(
                    dataOfHabits = viewModel.charts.value?.last() as Array<Any>,
                    title = "Completed in the last 7 Days",
                    nameOfCategory = arrayOf("M", "T", "W", "T", "F", "S", "S"))
            }
        }
    }

    private fun chartsMonth() {
        viewModel.isSelectedMonth.observe(this) {
            if (it) {
                Log.i("test", viewModel.charts.value.toString())
                charts(
                    dataOfHabits = viewModel.charts.value?.take(4)?.last() as Array<Any>,
                    title = "Completed in the this Month",
                    nameOfCategory = arrayOf("First Week",
                        "Second Week",
                        "Third Week",
                        "Fourth Week"))
            }
        }
    }

    private fun charts(dataOfHabits: Array<Any>, title: String, nameOfCategory: Array<String>) {
        val aaCharts =
            Charts(dataOfHabits = dataOfHabits, title = title, nameOfCategory = nameOfCategory)
        binding.aaChartView.aa_drawChartWithChartModel(aaCharts.drawCharts())
    }
}