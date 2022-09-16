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

    private val _navigateAddHabit = MutableLiveData<Event<Boolean>>()
    val navigateAddHabit: LiveData<Event<Boolean>>
        get() = _navigateAddHabit

    private val _navigateShowStatistics = MutableLiveData<Event<Boolean>>()
    val navigateShowStatistics: LiveData<Event<Boolean>>
        get() = _navigateShowStatistics

    private val _habits = MutableLiveData<List<Habit>>()
    private val allHabits: LiveData<List<Habit>> = repository.getAllHabit().asLiveData()

    private val selectedHabits = MutableLiveData<List<HabitResult>>()


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

            if (todayHabits == null || todayHabits.isEmpty()) {
                _doneToday.postValue(false)

            } else {
                _doneToday.postValue(true)
                if (todayHabits.isNotEmpty()) {
                    setHabits(todayHabits)
                }
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
        _navigateAddHabit.postValue(Event(true))
    }

    fun navigateToAddHabitStatistics() {
        _navigateShowStatistics.postValue(Event(true))
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
                selectedHabits.postValue(repository.isAnyHabitsInThisDay(Date().time))
            }
        }
    }


    fun isHabitSelected(habitID: Long): Boolean {
        selectedHabits.value?.let { it ->
            if (it.any { it.id_habit == habitID }) {
                return true
            }
        }
        return false
    }
}