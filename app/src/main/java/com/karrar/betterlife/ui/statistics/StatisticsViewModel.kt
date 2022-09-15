package com.karrar.betterlife.ui.statistics

import android.util.Log
import androidx.lifecycle.*
import com.karrar.betterlife.data.database.DataCharts
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.repository.BetterRepository
import kotlinx.coroutines.launch
import java.util.*

class StatisticsViewModel : ViewModel() {

    private val repository = BetterRepository()

    private val _charts = MutableLiveData<DataCharts>(DataCharts(mutableListOf(), mutableListOf()))
    val charts: LiveData<DataCharts>
        get() = _charts

    private val habits: LiveData<List<Habit>> = repository.getAllHabit().asLiveData()

    init {
        viewModelScope.launch {
            val date = repository.getTotalHabitPointInResult(166326658730, 166326658748)
            Log.i("test date", date.toString())
        }

        chartsDaily()
        getPrevSevenDaysFormattedDates()
    }

    fun chartsDaily() {
        viewModelScope.launch {
            repository.isAnyHabitsInThisDay(Date().time).forEach {
                _charts.postValue(DataCharts(mutableListOf(it.point), DAYS_OF_THE_WEEK))
            }
        }
    }

    fun chartsWeekly() {
        viewModelScope.launch {
            repository.getAllHabit().collect { it ->
                _charts.postValue(DataCharts(mutableListOf(it.sumOf { it.point }),
                    WEEKS_OF_THE_MONTH))
            }
        }
    }

    fun chartsMonthly() {
        viewModelScope.launch {
            repository.getAllHabit().collect { it ->
                _charts.postValue(DataCharts(mutableListOf(it.sumOf { it.point }),
                    MONTHS_OF_THE_YEAR))
            }
        }
    }

    fun getPrevSevenDaysFormattedDates(): ArrayList<Long> {
        val formattedDateList = ArrayList<Long>()

        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -7)
        for (i in 0..DEFAULT_END_DATE_DAYS) {
            val currentTime = calendar.time
            formattedDateList.add(currentTime.time)
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }
        return formattedDateList
    }


    companion object {
        const val DEFAULT_END_DATE_DAYS = 7

        val DAYS_OF_THE_WEEK = mutableListOf("S", "S", "M", "T", "W", "T", "F")
        val WEEKS_OF_THE_MONTH =
            mutableListOf("First Week", "Second Week", "Third Week", "Forth Week")
        val MONTHS_OF_THE_YEAR =
            mutableListOf("J", "F", "M", "A", "M", "J", "J", "A", "S", "O", "N", "D")
    }
}