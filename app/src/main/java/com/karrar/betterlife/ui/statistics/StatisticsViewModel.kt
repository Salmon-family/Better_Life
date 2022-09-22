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

    private val _count = MutableLiveData<Int>(0)
    private val daysCounter = MutableLiveData(0)
    private val weekCounter = MutableLiveData(0)

    private val _statisticsCases = MutableLiveData<StatisticsCases>(StatisticsCases.DAILY)
    val statisticsCases: LiveData<StatisticsCases>
        get() = _statisticsCases

    init {
        chartsForPreviousAndNextDay()
    }

    fun chartsWeekly() {
        val dailyList = mutableListOf<Any>()
        val daysName = mutableListOf<String>()

        _statisticsCases.postValue(StatisticsCases.WEEKLY)
        _count.postValue(0)

        val cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_YEAR, 30 * weekCounter.value!!)
        val startOfWeek = cal.time.time

        Log.e("TAG","${cal.get(Calendar.DAY_OF_MONTH)} ${cal.get(Calendar.MONTH)} ${cal.get(Calendar.WEEK_OF_YEAR)}")


        cal.add(Calendar.DAY_OF_YEAR, 30 * weekCounter.value!!)
        val endOfWeek = cal.time.time

        Log.e("TAG","${cal.get(Calendar.DAY_OF_MONTH)} ${cal.get(Calendar.MONTH)} ${cal.get(Calendar.WEEK_OF_YEAR)}")

        viewModelScope.launch {
            val points = repository.getPointsWeekly(startOfWeek,endOfWeek)
            for (point in points) {
                dailyList.add(point.pointsResult)
                val monthName =
                    android.text.format.DateFormat.format("d MMMM", point.dateResult).toString()
                daysName.add(monthName)
            }
            _charts.postValue(DataCharts(dailyList, daysName))
        }
    }

    fun nextDays() {
        daysCounter.value = daysCounter.value?.plus(1)
        weekCounter.value = weekCounter.value?.plus(1)
        _count.value = _count.value?.plus(1)
        when (_statisticsCases.value) {
            StatisticsCases.DAILY -> chartsForPreviousAndNextDay()
            StatisticsCases.WEEKLY -> chartsWeekly()
            StatisticsCases.MONTHLY -> chartsForPreviousAndNextMonth()
            else -> {}
        }
    }

    fun previousDays() {
        daysCounter.value = daysCounter.value?.minus(1)
        weekCounter.value = weekCounter.value?.minus(1)
        _count.value = _count.value?.minus(1)
        when (_statisticsCases.value) {
            StatisticsCases.DAILY -> chartsForPreviousAndNextDay()
            StatisticsCases.WEEKLY -> chartsWeekly()
            StatisticsCases.MONTHLY -> chartsForPreviousAndNextMonth()
            else -> {}
        }
    }

    fun chartsForPreviousAndNextDay() {
        val dailyList = mutableListOf<Any>()
        val daysName = mutableListOf<String>()

        _statisticsCases.postValue(StatisticsCases.DAILY)

        val cal = Calendar.getInstance()

        cal.add(Calendar.DAY_OF_YEAR, 6 * daysCounter.value!!)
        val startOfWeek = cal.time.time
        Log.e("TAG", "start ${cal.get(Calendar.DAY_OF_MONTH)}  ${cal.get(Calendar.MONTH)}")
        cal.add(Calendar.DAY_OF_YEAR, 6 * -1)
        val endOfWeek = cal.time.time
        Log.e("TAG", "end ${cal.get(Calendar.DAY_OF_MONTH)}  ${cal.get(Calendar.MONTH)}")

        viewModelScope.launch {
            val points = repository.getPointsInRange(endOfWeek, startOfWeek)
            for (point in points) {
                dailyList.add(point.pointsResult)
                val dayName =
                    android.text.format.DateFormat.format("EEEE", point.dateResult).toString()
                daysName.add(dayName)
            }
            _charts.postValue(DataCharts(dailyList, daysName))
        }
    }

    fun chartsForPreviousAndNextMonth() {
        val dailyList = mutableListOf<Any>()
        val daysName = mutableListOf<String>()

        _statisticsCases.postValue(StatisticsCases.MONTHLY)

        val cal = Calendar.getInstance()
        val counter = _count.value ?: 0
        cal.add(Calendar.YEAR, counter)
        val nextYear = cal.time.time

        Log.i("TAG YEAR", "nextYear: ${cal.get(Calendar.YEAR)} count: ${_count.value}")

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