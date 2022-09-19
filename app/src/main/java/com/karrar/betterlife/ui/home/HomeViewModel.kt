package com.karrar.betterlife.ui.home

import androidx.lifecycle.*
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.database.entity.HabitResult
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

    val todayHabitsList = MutableLiveData<List<Int>>()

    init {
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
            todayHabitsList.value?.forEach { position ->
                val habit = allHabits.value?.get(position - 1)
                habit?.let {
                    repository.insertTodayHabit(
                        HabitResult(
                            id_habit = habit.id, point = habit.point, date = Date()
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