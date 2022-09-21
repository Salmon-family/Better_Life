package com.karrar.betterlife.ui.statistics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karrar.betterlife.data.DataCharts
import com.karrar.betterlife.data.repository.BetterRepository
import kotlinx.coroutines.launch
import java.util.*

class StatisticsViewModel : ViewModel() {

    private val repository = BetterRepository()

    private val _charts = MutableLiveData<DataCharts>()
    val charts: LiveData<DataCharts>
        get() = _charts

    init {
        chartsDaily()
    }

    fun chartsDaily() {
        val dailyList = mutableListOf<Any>()
        val daysName = mutableListOf<String>()

        val cal = Calendar.getInstance()
        val endOfWeek = cal.time.time
        cal.add(Calendar.DAY_OF_YEAR, -6)
        val startOfWeek = cal.time.time

        viewModelScope.launch {
            val points = repository.getPointsInRange(startOfWeek, endOfWeek)
            for (point in points) {
                dailyList.add(point.pointsResult)

                val dayName =
                    android.text.format.DateFormat.format("EEEE", point.dateResult).toString()
                daysName.add(dayName)
            }
            _charts.postValue(DataCharts(dailyList, daysName))
        }
    }

    fun chartsMonthly() {
        val dailyList = mutableListOf<Any>()
        val daysName = mutableListOf<String>()
        val cal = Calendar.getInstance()
        cal.add(Calendar.YEAR, +1)
        val nextYear = cal.time.time

        viewModelScope.launch {
            val points = repository.getPointDuringYearWithDate(nextYear)
            for (point in points) {
                dailyList.add(point.pointsResult)
                val monthname =
                    android.text.format.DateFormat.format("MMMM", point.dateResult).toString()
                daysName.add(monthname)
            }
            _charts.postValue(DataCharts(dailyList, daysName))
        }
    }

    fun chartsWeekly() {
        val dailyList = mutableListOf<Any>()
        val daysName = mutableListOf<String>()

        viewModelScope.launch {
            val points = repository.getPointsWeekly()
            for (point in points) {
                dailyList.add(point.pointsResult)
                val monthname =
                    android.text.format.DateFormat.format("d MMMM", point.dateResult).toString()
                daysName.add(monthname)
            }
            _charts.postValue(DataCharts(dailyList, daysName))
        }
    }

}