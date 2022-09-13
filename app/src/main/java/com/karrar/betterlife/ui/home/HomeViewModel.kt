package com.karrar.betterlife.ui.home

import androidx.lifecycle.*
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.database.entity.HabitResult
import com.karrar.betterlife.data.repository.BetterRepository
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel : ViewModel() {

    private val repository = BetterRepository()

    val habits: LiveData<List<Habit>> = repository.getAllHabit().asLiveData()

    val checkedBtnObs = MutableLiveData<Int>()

    fun addFakeData() {
        viewModelScope.launch {
            val num = habits.value?.size?.plus(1) ?: 0
            repository.insertNewHabit(Habit(name = "category#$num", point = num))
        }
    }

    fun setTodayHabit(habitID: Long) {
        viewModelScope.launch {
            val habit = repository.getHabitByID(habitID)
            repository.insertTodayHabit(
                HabitResult(
                    id_habit = habit.id,
                    point = habit.point,
                    date = Date()
                )
            )
        }
    }
}