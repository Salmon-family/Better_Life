package com.karrar.betterlife.ui.statistics

import android.util.Log
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

    private val _count = MutableLiveData<Int>(1)
    val count: LiveData<Int>
        get() = _count

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

    fun dailyCount() {
        _count.postValue(_count.value?.plus(1))
        chartsLastDaily()
    }

    fun chartsLastDaily() {
        val dailyList = mutableListOf<Any>()
        val daysName = mutableListOf<String>()
        val cal = Calendar.getInstance()

        var counter = _count.value?:1
        cal.add(Calendar.DAY_OF_YEAR, -6 * counter--)
        val endOfWeek = cal.time.time

        cal.add(Calendar.DAY_OF_YEAR, -6 * _count.value!!)
        val startOfWeek = cal.time.time

        Log.i("TAG", "chartsLastDaily: $endOfWeek $startOfWeek count: ${_count.value!!}")

        viewModelScope.launch {
            val points = repository.getPointsInRange(startOfWeek, endOfWeek)
            for (point in points) {
                Log.i("test points result", "chartsLastDaily: ${point.dateResult}")
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

    fun chartsYearly() {
        val dailyList = mutableListOf<Any>()
        val daysName = mutableListOf<String>()

        val cal = Calendar.getInstance()
        cal.add(Calendar.YEAR, -1)
        val lastYear = cal.time.time

        viewModelScope.launch {
            val points = repository.getPointDuringYearWithDate(lastYear)
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