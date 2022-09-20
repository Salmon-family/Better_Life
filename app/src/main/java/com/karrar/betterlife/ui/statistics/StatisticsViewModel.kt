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
        val date = Date()
        viewModelScope.launch {

            //get Date
            val firstDayOfWeek = repository.getdayID(Date().time)
            val lastDayOfWeek = repository.getdayID(Date().time - 604800000L)

            val points = if (lastDayOfWeek != null) {
                repository.getHabitsByRangOfDays(firstDayOfWeek.dayID, lastDayOfWeek.dayID)
            } else {
                repository.getHabitsByDay(Long.MAX_VALUE)
            }

            for (i in 0..points.lastIndex) {
                dailyList.add(points[i].sumPoints())
                daysName.add(DAYS_OF_THE_WEEK[i])
                date.time -= ONE_DAY_IN_MILLISECOND
            }
            _charts.postValue(DataCharts(dailyList, daysName))
        }
    }

    companion object {
        const val DEFAULT_END_DATE_DAYS = 7
        const val ONE_DAY_IN_MILLISECOND = 86400000
        val DAYS_OF_THE_WEEK = mutableListOf("S", "M", "T", "W", "T", "F", "S")
        val WEEKS_OF_THE_MONTH =
            mutableListOf("First Week", "Second Week", "Third Week", "Forth Week")
        val MONTHS_OF_THE_YEAR =
            mutableListOf("J", "F", "M", "A", "M", "J", "J", "A", "S", "O", "N", "D")
    }
}