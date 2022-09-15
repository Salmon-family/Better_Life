package com.karrar.betterlife.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.database.entity.HabitResult
import com.karrar.betterlife.data.repository.BetterRepository
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel : ViewModel() {
    private val repository = BetterRepository()

    private val _navigateAddHabit = MutableLiveData<Boolean>()
    val navigateAddHabit: LiveData<Boolean>
        get() = _navigateAddHabit

    private val _navigateShowStatistics = MutableLiveData<Boolean>()
    val navigateShowStatistics: LiveData<Boolean>
        get() = _navigateShowStatistics

    private val _habits = MutableLiveData<List<Habit>>()
    private val allHabits: LiveData<List<Habit>> = repository.getAllHabit().asLiveData()

    val habits = MediatorLiveData<List<Habit>>().apply {
        addSource(allHabits, this@HomeViewModel::setHabitList)
        addSource(_habits, this@HomeViewModel::setHabitList)
    }


    private val _doneToday = MutableLiveData(false)
    val doneToday: LiveData<Boolean>
        get() = _doneToday

    init {
        isDoneForToday()
    }

    private fun setHabitList(value: List<Habit>) {
        if (_habits.value?.isNotEmpty() == true) {
            habits.postValue(_habits.value)
        } else {
            habits.postValue(allHabits.value)
        }
    }

    private fun isDoneForToday() {
        viewModelScope.launch {
            val todayHabits = repository.isAnyHabitsInThisDay(Date().time)
            _doneToday.postValue(todayHabits.isNotEmpty())
            if (todayHabits.isNotEmpty()) {
                setHabits(todayHabits)
            }
        }
    }

    private fun setHabits(todayHabits: List<HabitResult>) {
        viewModelScope.launch {
            val list = todayHabits.map { it.id_habit }
            val output = repository.getAllHabitByIDs(list)
            _habits.postValue(output)
        }
    }

    fun navigateToAddHabitDialog() {
        _navigateAddHabit.postValue(true)
    }

    fun navigateToAddHabitStatistics() {
        _navigateShowStatistics.postValue(true)
    }

    fun setTodayHabit(habitID: Long) {
        viewModelScope.launch {
            val habit = repository.getHabitByID(habitID)
            habit?.let {
                val selectedHabit = repository.getTodayHabit(habitID)
                if (selectedHabit == null) {
                    repository.insertTodayHabit(
                        HabitResult(
                            id_habit = habit.id,
                            point = habit.point,
                            date = Date()
                        )
                    )
                } else {
                    repository.deleteHabit(selectedHabit)
                }
            }
        }
    }

}