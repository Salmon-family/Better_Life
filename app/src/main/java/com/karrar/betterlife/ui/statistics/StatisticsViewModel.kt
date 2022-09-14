package com.karrar.betterlife.ui.statistics

import android.util.Log
import androidx.lifecycle.*
import com.karrar.betterlife.data.database.DataCharts
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.database.entity.HabitResult
import com.karrar.betterlife.data.repository.BetterRepository
import kotlinx.coroutines.launch
import java.util.*

class StatisticsViewModel : ViewModel() {

    private val repository = BetterRepository()

    private val _charts = MutableLiveData<DataCharts>(DataCharts(arrayOf(20), arrayOf("S")))
    val charts: LiveData<DataCharts>
        get() = _charts

    val habits: LiveData<List<Habit>> = repository.getAllHabit().asLiveData()
    val checkedBtnObs = MutableLiveData<Int>()

    val isSelectedDay = MutableLiveData(false)
    val isSelectedWeek = MutableLiveData(false)
    val isSelectedMonth = MutableLiveData(false)

    init {
        addItemsInChartsList()
    }

    private fun addItemsInChartsList() { }


    fun chartsDaily() {
        isSelectedDay.postValue(true)
    }

    fun chartsWeekly() {
        isSelectedWeek.postValue(true)
    }

    fun chartsMonthly() {
        isSelectedMonth.postValue(true)
    }


}