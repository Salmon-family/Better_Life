package com.karrar.betterlife.ui.statistics

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StatisticsViewModel : ViewModel() {

    private val _charts = MutableLiveData<ArrayList<Array<Int>>>()
    val charts: LiveData<ArrayList<Array<Int>>>
        get() = _charts


    val isSelectedWeek = MutableLiveData(false)
    val isSelectedMonth = MutableLiveData(false)

    init {
        addItemsInChartsList()
    }

    private fun addItemsInChartsList() {
        _charts.postValue(arrayListOf(
            arrayOf(7, 6, 9, 14, 18, 21, 28),
            arrayOf(7, 6, 9, 14, 18, 21, 28),
            arrayOf(7, 6, 9, 14, 18, 21, 28),
            arrayOf(7, 6, 9, 14, 18, 21, 28),
            arrayOf(7, 6, 9, 14, 18, 21, 28),
            arrayOf(7, 6, 9, 14, 18, 21, 28),
        ))
    }


    fun chartsWeek() {
        isSelectedWeek.postValue(true)
    }

    fun chartsMonth() {
        isSelectedMonth.postValue(true)
    }


}