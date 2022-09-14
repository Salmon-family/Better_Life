package com.karrar.betterlife.ui.statistics

import android.util.Log
import androidx.lifecycle.*
import com.karrar.betterlife.data.ChartsCases
import com.karrar.betterlife.data.database.DataCharts
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.database.entity.HabitResult
import com.karrar.betterlife.data.repository.BetterRepository
import kotlinx.coroutines.launch
import java.util.*

class StatisticsViewModel : ViewModel() {

    private val repository = BetterRepository()

    private val _charts = MutableLiveData<DataCharts>(DataCharts(listOf(), listOf()))
    val charts: LiveData<DataCharts>
        get() = _charts

    private val _chartsCases = MutableLiveData<ChartsCases>()
    val chartsCases: LiveData<ChartsCases>
        get() = _chartsCases

    val habits: LiveData<List<Habit>> = repository.getAllHabit().asLiveData()
    val checkedBtnObs = MutableLiveData<Int>()

    val isSelectedDate = MutableLiveData(ChartsCases.DAILY)

    init {
        showAllCases()
    }

    private fun showAllCases() {
        _chartsCases.value?.let { state ->
            when (state) {
                ChartsCases.DAILY -> _charts.postValue(DataCharts(listOf(20, 30, 33, 12, 16, 9, 13),
                    listOf("S", "S", "M", "T", "W", "T", "F")))
                ChartsCases.WEEKLY -> _charts.postValue(DataCharts(listOf(20, 30, 33, 12),
                    listOf("First Week", "Second Week", "Third Week", "Forth Week")))
                ChartsCases.MONTHLY -> _charts.postValue(DataCharts(listOf(20, 30, 33, 12, 20, 30, 33),
                    listOf("J", "F", "M", "A", "J", "J", "O")))
            }
        }

    }


    fun chartsDaily() {
        isSelectedDate.postValue(ChartsCases.DAILY)
    }

    fun chartsWeekly() {
        isSelectedDate.postValue(ChartsCases.WEEKLY)
    }

    fun chartsMonthly() {
        isSelectedDate.postValue(ChartsCases.MONTHLY)
    }


}