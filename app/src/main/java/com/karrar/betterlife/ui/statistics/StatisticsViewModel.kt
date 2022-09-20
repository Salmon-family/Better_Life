package com.karrar.betterlife.ui.statistics

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karrar.betterlife.data.database.DataCharts
import com.karrar.betterlife.data.repository.BetterRepository
import com.karrar.betterlife.util.toDate
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
        val date = Date()
        viewModelScope.launch {
            for (i in 0..6) {
                Log.i("test time", date.time.toDate().toString())
                val points = repository.getTotalPointsOfDay(date.time.toDate())
                dailyList.add(points)
                daysName.add(DAYS_OF_THE_WEEK[date.day])
                date.time -= ONE_DAY_IN_MILLISECOND
            }
            _charts.postValue(DataCharts(dailyList, daysName))
        }
    }

    fun chartsMonthly() {
        val monthlyList = mutableListOf<Any>()
        val monthsName = mutableListOf<String>()
        val date = Date()
        viewModelScope.launch {
            for (i in 0..11) { // 12 months
                Log.i("monthly time", date.time.toString())
                val points = repository.getTotalPointsOfMonth(ONE_MONTH_IN_MILLISECOND.toLong(),
                    date.time.toDate())
                monthlyList.add(points)
                monthsName.add(MONTHS_OF_THE_YEAR[date.month])
                date.time += ONE_MONTH_IN_MILLISECOND
            }
            _charts.postValue(DataCharts(monthlyList, monthsName))
        }
    }

    fun chartsLastYear() {
        val lastYearList = mutableListOf<Any>()
        val yearsName = mutableListOf<String>()
        val date = Date()
        viewModelScope.launch {
            for (i in 0..11) { // 12 months
                Log.i("monthly time", date.time.toString())
                val points = repository.getTotalPointsOfMonth(ONE_MONTH_IN_MILLISECOND.toLong(),
                    date.time.toDate())
                lastYearList.add(points)
                yearsName.add(MONTHS_OF_THE_YEAR[date.month])
                date.time += ONE_MONTH_IN_MILLISECOND
            }
            _charts.postValue(DataCharts(lastYearList, yearsName))
        }
    }

    fun chartsLastWeeks(){
        for (i in 0..1){
            chartsLastWeek()
        }
    }

    fun chartsLastWeek() {
        val lastWeekList = mutableListOf<Any>()
        val daysName = mutableListOf<String>()
        val date = Date()
        viewModelScope.launch {
            date.time -= ONE_DAY_IN_MILLISECOND * 7
            for (i in 0..6) {
                Log.i("test time", date.time.toDate().toString())
                val points =
                    repository.getTotalPointsOfMonth(ONE_DAY_IN_MILLISECOND.toLong().toDate(),
                        date.time.toDate())
                lastWeekList.add(points)
                daysName.add(DAYS_OF_THE_WEEK[date.day])
                date.time -= ONE_DAY_IN_MILLISECOND
            }
            _charts.postValue(DataCharts(lastWeekList, daysName))
        }
    }

    companion object {
        private const val DEFAULT_END_DATE_DAYS = 7
        private const val ONE_DAY_IN_MILLISECOND = 86400000
        private const val ONE_MONTH_IN_MILLISECOND = 86400000 * 30
        private val DAYS_OF_THE_WEEK = mutableListOf("S", "M", "T", "W", "T", "F", "S")
        private val MONTHS_OF_THE_YEAR =
            mutableListOf("J", "F", "M", "A", "M", "J", "J", "A", "S", "O", "N", "D")
    }
}