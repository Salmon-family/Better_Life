package com.karrar.betterlife.ui.statistics

import android.util.Log
import androidx.lifecycle.*
import com.karrar.betterlife.data.database.DataCharts
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.repository.BetterRepository
import com.karrar.betterlife.util.formatDate
import com.karrar.betterlife.util.minusDaysFromCurrentDayInMilliSeconds
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class StatisticsViewModel : ViewModel() {

    private val repository = BetterRepository()

    private val _charts = MutableLiveData<DataCharts>()
    val charts: LiveData<DataCharts>
        get() = _charts

    private val habits: LiveData<List<Habit>> = repository.getAllHabit().asLiveData()

    init {
        chartsDaily()
    }

    fun chartsDaily() {
        val dailyList = mutableListOf<Any>()
        val daysName = mutableListOf<String>()
        val date = Date()
        viewModelScope.launch {
            for (i in 0..6) {
                Log.i("test time", date.time.toString())
                val points = repository.getTotalPointsOfDay(date.time)
                dailyList.add(points)
                daysName.add(date.time.formatDate("EE"))
                date.time -= 86400000
            }
            _charts.postValue(DataCharts(dailyList, daysName))
        }
    }

    fun chartsWeekly() {
        viewModelScope.launch {
            val day =
                repository.getTotalHabitPointInResult(Date().minusDaysFromCurrentDayInMilliSeconds(7),
                    Date().time)
            repository.getAllResultHabit().collect {
                _charts.postValue(DataCharts(mutableListOf(day), WEEKS_OF_THE_MONTH))
            }
        }
    }

    fun chartsMonthly() {
        viewModelScope.launch {
            val day =
                repository.getTotalHabitPointInResult(Date().minusDaysFromCurrentDayInMilliSeconds(7),
                    Date().time)
            repository.getAllResultHabit().collect {
                _charts.postValue(DataCharts(mutableListOf(day), MONTHS_OF_THE_YEAR))
            }
        }
    }

//    fun getPrevSevenDaysFormattedDates(): ArrayList<Long> {
//        val formattedDateList = ArrayList<Long>()
//
//        val calendar = Calendar.getInstance()
//        calendar.add(Calendar.DAY_OF_YEAR, -7)
//        for (i in 0..DEFAULT_END_DATE_DAYS) {
//            val currentTime = calendar.time
//            formattedDateList.add(currentTime)
//            calendar.add(Calendar.DAY_OF_YEAR, 1)
//        }
//        return formattedDateList
//    }

//    fun subtractDate(time: String?, subtractDay: Int): String {
//        val cal = Calendar.getInstance()
//        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH)
//        cal.time = sdf.parse(time)
//        cal.add(Calendar.DATE, -subtractDay)
//        val wantedDate: String = sdf.format(cal.time)
//        Log.d("tag", wantedDate)
//        return wantedDate
//    }


    companion object {
        const val DEFAULT_END_DATE_DAYS = 7

        val DAYS_OF_THE_WEEK = mutableListOf("S", "S", "M", "T", "W", "T", "F")
        val WEEKS_OF_THE_MONTH =
            mutableListOf("First Week", "Second Week", "Third Week", "Forth Week")
        val MONTHS_OF_THE_YEAR =
            mutableListOf("J", "F", "M", "A", "M", "J", "J", "A", "S", "O", "N", "D")
    }
}