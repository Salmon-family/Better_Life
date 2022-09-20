package com.karrar.betterlife.ui.home

import androidx.lifecycle.*
import com.karrar.betterlife.data.database.entity.DailyHabits
import com.karrar.betterlife.data.database.entity.Day
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.repository.BetterRepository
import com.karrar.betterlife.util.Event
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel : ViewModel() {

    private val repository = BetterRepository()

    private val _navigateShowStatistics = MutableLiveData<Event<Boolean>>()
    val navigateShowStatistics: LiveData<Event<Boolean>>
        get() = _navigateShowStatistics

    val allHabits: LiveData<List<Habit>> = repository.getAllHabit().asLiveData()

    private val _doneToday = MutableLiveData(false)
    val doneToday: LiveData<Boolean>
        get() = _doneToday

    val todayHabitsList = MutableLiveData<List<String>>()

    init {
        //for test
//        viewModelScope.launch {
//            for (i in 0..10)
//                repository.insertNewHabit(Habit(name = "test$i", point = 10 + i))
//        }
        /////

        isDoneForToday()
    }

    private fun isDoneForToday() {
        viewModelScope.launch {
            val todayHabits = repository.isAnyHabitsInThisDay(Date().time)
            _doneToday.postValue(!todayHabits.isNullOrEmpty())
        }
    }

    fun setDoneToday() {
        saveData()
        _doneToday.postValue(true)
    }

    private fun saveData() {
        viewModelScope.launch {
            val day = Day(date = Date())
            repository.insertToday(day)
            val dayToInsert = repository.getdayID(day.date.time)
            todayHabitsList.value?.forEach { name ->
                val habit = allHabits.value?.first {
                    it.name == name
                }
                habit?.let {
                    repository.insertTodayHabits(
                        DailyHabits(
                            dayID = dayToInsert.dayID,
                            habitID = habit.habitID
                        )
                    )
                }
            }
        }
    }

    fun navigateToAddHabitStatistics() {
        _navigateShowStatistics.postValue(Event(true))
    }

}