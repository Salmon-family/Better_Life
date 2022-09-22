package com.karrar.betterlife.ui.home

import androidx.lifecycle.*
import com.karrar.betterlife.data.database.entity.DailyHabits
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.repository.BetterRepository
import com.karrar.betterlife.util.Event
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel : ViewModel() {

    private val repository = BetterRepository()

    val allHabits: LiveData<List<Habit>> = repository.getAllHabit().asLiveData()

    private val _doneToday = MutableLiveData(false)
    val doneToday: LiveData<Boolean>
        get() = _doneToday

    val todayHabitsList = MutableLiveData<List<String>>()

    init {
        isDoneForToday()
        setDefaultHabits()
    }

    private fun isDoneForToday() {
        viewModelScope.launch {
            val todayHabits = repository.isAnyHabitsInThisDay(Date().time)
            _doneToday.postValue(!todayHabits.isNullOrEmpty())
        }
    }

    private fun setDefaultHabits() {
        viewModelScope.launch {
            if (allHabits.value.isNullOrEmpty()) {
                val habits = mutableListOf<Habit>()
                for (i in 0..10) {
                    if (i % 2 == 0) {
                        habits.add(Habit(name = "test$i", point = 10 + i))
                    } else {
                        habits.add(Habit(name = "test$i", point = (10 + i) * -1))
                    }
                }
                repository.insertHabits(habits)
            }
        }
    }

    fun setDoneToday() {
        saveData()
        _doneToday.postValue(true)
    }

    private fun saveData() {
        viewModelScope.launch {
            val habitsPerDay = mutableListOf<DailyHabits>()
            todayHabitsList.value?.forEach { name ->
                val habit = allHabits.value?.first {
                    it.name == name
                }
                habit?.let {
                    habitsPerDay.add(
                        DailyHabits(dayID = Date(), habitID = habit.habitID)
                    )
                }
            }
            repository.insertAllHabitsPerDay(habitsPerDay)
        }
    }

}