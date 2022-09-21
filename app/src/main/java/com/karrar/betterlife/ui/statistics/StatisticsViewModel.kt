package com.karrar.betterlife.ui.statistics

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karrar.betterlife.data.DataCharts
import com.karrar.betterlife.data.database.StatisticsCases
import com.karrar.betterlife.data.repository.BetterRepository
import kotlinx.coroutines.launch
import java.util.*

class StatisticsViewModel : ViewModel() {

    private val repository = BetterRepository()

    private val _charts = MutableLiveData<DataCharts>()
    val charts: LiveData<DataCharts>
        get() = _charts

    private val _counter = MutableLiveData<Int>(0)
    val count: LiveData<Int>
        get() = _counter

    private val _statisticsCases = MutableLiveData<StatisticsCases>()
    val statisticsCases: LiveData<StatisticsCases>
        get() = _statisticsCases

    init {
        chartsForPreviousAndNextDay(6)
    }

    fun chartsDaily() {
        val dailyList = mutableListOf<Any>()
        val daysName = mutableListOf<String>()

        _statisticsCases.postValue(StatisticsCases.DAILY)
        _counter.postValue(0)

        val cal = Calendar.getInstance()
        val endOfWeek = cal.time.time
        cal.add(Calendar.DAY_OF_YEAR, -6)
        val startOfWeek = cal.time.time

        Log.i("TAG DAILY", "startOfWeek: $startOfWeek endOfWeek: $endOfWeek count: ${_counter.value}")

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

    fun chartsWeekly() {
        val dailyList = mutableListOf<Any>()
        val daysName = mutableListOf<String>()

        _statisticsCases.postValue(StatisticsCases.WEEKLY)
        _counter.postValue(0)

        viewModelScope.launch {
            val points = repository.getPointsWeekly()
            for (point in points) {
                dailyList.add(point.pointsResult)
                val monthName =
                    android.text.format.DateFormat.format("d MMMM", point.dateResult).toString()
                daysName.add(monthName)
            }
            _charts.postValue(DataCharts(dailyList, daysName))
        }
    }

    fun chartsMonthly() {
        val dailyList = mutableListOf<Any>()
        val daysName = mutableListOf<String>()

        _statisticsCases.postValue(StatisticsCases.MONTHLY)
        _counter.postValue(0)


        val cal = Calendar.getInstance()
        cal.add(Calendar.YEAR, +1)
        val nextYear = cal.time.time

        Log.i("TAG YEAR", "nextYear: $nextYear count: ${_counter.value}")

        viewModelScope.launch {
            val points = repository.getPointDuringYearWithDate(nextYear)
            for (point in points) {
                dailyList.add(point.pointsResult)
                val monthName =
                    android.text.format.DateFormat.format("MMMM", point.dateResult).toString()
                daysName.add(monthName)
            }
            _charts.postValue(DataCharts(dailyList, daysName))
        }
    }


    fun nextDays() {
        var increaseCounter = _counter.value ?: 0
        _counter.postValue(++increaseCounter)
        when (_statisticsCases.value) {
            StatisticsCases.DAILY -> chartsForPreviousAndNextDay(6)
            StatisticsCases.WEEKLY -> chartsForPreviousAndNextWeek()
            StatisticsCases.MONTHLY -> chartsForPreviousAndNextMonth(1)
            else -> {}
        }
    }

    fun previousDays() {
        var decreaseCounter = _counter.value ?: 0
        _counter.postValue(--decreaseCounter)
        when (_statisticsCases.value) {
            StatisticsCases.DAILY -> chartsForPreviousAndNextDay(6)
            StatisticsCases.WEEKLY -> chartsForPreviousAndNextWeek()
            StatisticsCases.MONTHLY -> chartsForPreviousAndNextMonth(-1)
            else -> {}
        }
    }

    private fun chartsForPreviousAndNextDay(count: Int) {
        val dailyList = mutableListOf<Any>()
        val daysName = mutableListOf<String>()

        _statisticsCases.postValue(StatisticsCases.DAILY)

        val cal = Calendar.getInstance()
        var counter = _counter.value ?: 0
        cal.add(Calendar.DAY_OF_YEAR, count * _counter.value!!)
        val startOfWeek = cal.time.time

        cal.add(Calendar.DAY_OF_YEAR, count * --counter)
        val endOfWeek = cal.time.time


        Log.i("TAG DAILY", "startOfWeek: $startOfWeek endOfWeek: $endOfWeek count: ${_counter.value}")

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

    private fun chartsForPreviousAndNextWeek() {
        _statisticsCases.postValue(StatisticsCases.WEEKLY)

    }

    private fun chartsForPreviousAndNextMonth(count: Int) {
        val dailyList = mutableListOf<Any>()
        val daysName = mutableListOf<String>()

        _statisticsCases.postValue(StatisticsCases.MONTHLY)

        val cal = Calendar.getInstance()
        var counter = _counter.value ?: 1
        cal.add(Calendar.YEAR, count * --counter)
        val nextYear = cal.time.time

        Log.i("TAG YEAR", "nextYear: $nextYear count: ${_counter.value}")

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


}