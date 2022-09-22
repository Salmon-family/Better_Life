package com.karrar.betterlife.ui.statistics

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karrar.betterlife.data.DataCharts
import com.karrar.betterlife.data.database.StatisticsCases
import com.karrar.betterlife.data.repository.BetterRepository
import com.karrar.betterlife.util.toStringDate
import kotlinx.coroutines.launch
import java.util.*

class StatisticsViewModel : ViewModel() {

    private val repository = BetterRepository()

    private val _charts = MutableLiveData<DataCharts>()
    val charts: LiveData<DataCharts>
        get() = _charts

    private val _habit = MutableLiveData<String>()
    val habit: LiveData<String>
        get() = _habit

    private val _count = MutableLiveData<Int>(0)
    private val daysCounter = MutableLiveData(0)
    private val weekCounter = MutableLiveData(0)

    private val _statisticsCases = MutableLiveData(StatisticsCases.DAILY)

    init {
        chartDaily(1)
    }

    fun nextDays() {
        daysCounter.value = daysCounter.value?.plus(1)
        _count.value = _count.value?.plus(1)
        weekCounter.value = weekCounter.value?.plus(1)
        when (_statisticsCases.value) {
            StatisticsCases.DAILY -> chartsForPreviousAndNextDay()
            StatisticsCases.WEEKLY -> chartsWeekly2()
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
            StatisticsCases.WEEKLY -> chartsWeekly2()
            StatisticsCases.MONTHLY -> chartsForPreviousAndNextMonth()
            else -> {}
        }
    }

    fun chartDaily(state: Int) {
        daysCounter.value = 0
        weekCounter.value = 0
        _count.value = 0
        when (state) {
            1 -> chartsForPreviousAndNextDay()
            2 -> chartsWeekly2()
            3 -> chartsForPreviousAndNextMonth()
        }
    }

    private fun chartsForPreviousAndNextDay() {
        val dailyList = mutableListOf<Any>()
        val daysName = mutableListOf<String>()

        _statisticsCases.postValue(StatisticsCases.DAILY)

        val cal = Calendar.getInstance()
        if (daysCounter.value!! <= 0) {
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
                if (points.isNotEmpty()) {
                    _charts.postValue(DataCharts(dailyList, daysName))
                    _habit.postValue(
                        "${endOfWeek.toStringDate("MMM dd")} - ${
                            startOfWeek.toStringDate(
                                "MMM dd yyyy"
                            )
                        }"
                    )
                }
            }
        } else {
            daysCounter.value = 0
        }
    }

//    private fun chartsWeekly() {
//        val dailyList = mutableListOf<Any>()
//        val daysName = mutableListOf<String>()
//
//        _statisticsCases.postValue(StatisticsCases.WEEKLY)
//        val cal = Calendar.getInstance()
//        if (weekCounter.value!! <= 0) {
//            cal.add(Calendar.MONTH, 1 * weekCounter.value!!)
//            val firstofMonth = cal.time.time
//
//            val monthName = android.text.format.DateFormat.format("MMMM", firstofMonth).toString()
//
//            cal.add(Calendar.MONTH, 1)
//            val endofMonth = cal.time.time
//
//            Log.e("TAG", "$firstofMonth , $endofMonth")
//
//            viewModelScope.launch {
//                val points =
//                    repository.getPointsWeekly(firstofMonth, endofMonth)
//                for (point in points) {
//                    dailyList.add(point.pointsResult)
//                    val monthName =
//                        android.text.format.DateFormat.format("d MMMM", point.dateResult).toString()
//                    daysName.add(monthName)
//                }
//                if (points.isNotEmpty()) {
//                    _charts.postValue(DataCharts(dailyList, daysName))
//                    _habit.postValue(monthName)
//                }
//            }
//
//        } else {
//            weekCounter.value = 0
//        }
//    }

    private fun chartsWeekly2() {
        val dailyList = mutableListOf<Any>()
        val daysName = mutableListOf<String>()

        _statisticsCases.postValue(StatisticsCases.WEEKLY)
        val cal = Calendar.getInstance(Locale.US)
        if (weekCounter.value!! <= 0) {
            cal.add(Calendar.DAY_OF_YEAR, 7 * weekCounter.value!!)
            val firstofMonth = cal.time.time

            cal.add(Calendar.DAY_OF_YEAR, 7 * -1)
            val endofMonth = cal.time.time

            cal.add(Calendar.DAY_OF_YEAR, 7 * -1)
            val thirdofMonth = cal.time.time

            cal.add(Calendar.DAY_OF_YEAR, 7 * -1)
            val forthofMonth = cal.time.time

            cal.add(Calendar.DAY_OF_YEAR, 7 * -1)
            val fifthfMonth = cal.time.time

            Log.e("TAGDATE", "$firstofMonth , $endofMonth $thirdofMonth $forthofMonth $fifthfMonth")

            viewModelScope.launch {
                val points =
                    repository.getPointsWeekly2(fifthfMonth, forthofMonth,thirdofMonth,endofMonth, firstofMonth )
                for (point in points) {
                    dailyList.add(point.pointsResult)
                    val monthName =
                        android.text.format.DateFormat.format("d MMMM", point.dateResult).toString()
                    daysName.add(monthName)
                }
                if (points.isNotEmpty()) {
                    _charts.postValue(DataCharts(dailyList, daysName))
//                    _habit.postValue(monthName)
                }
            }

        } else {
            weekCounter.value = 0
        }
    }

    private fun chartsForPreviousAndNextMonth() {
        val dailyList = mutableListOf<Any>()
        val daysName = mutableListOf<String>()

        _statisticsCases.postValue(StatisticsCases.MONTHLY)

        val cal = Calendar.getInstance()
        if (_count.value!! <= 0) {
            val counter = _count.value ?: 0
            cal.add(Calendar.YEAR, counter)
            val nextYear = cal.time.time

            Log.i("TAG YEAR", "nextYear: ${cal.get(Calendar.YEAR)} count: ${_count.value}")

            viewModelScope.launch {
                val points = repository.getPointDuringYearWithDate(nextYear)
                for (point in points) {
                    dailyList.add(point.pointsResult)
                    val monthName =
                        android.text.format.DateFormat.format("MMMM", point.dateResult).toString()
                    daysName.add(monthName)
                }
                if (points.isNotEmpty()) {
                    _charts.postValue(DataCharts(dailyList, daysName))
                    _habit.postValue(nextYear.toStringDate("yyyy"))
                }
            }
        } else {
            _count.value = 0
        }
    }

}