package com.karrar.betterlife.ui.habits.deleteHabitDialog

import androidx.lifecycle.*
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.repository.BetterRepository
import com.karrar.betterlife.util.Event
import kotlinx.coroutines.launch

class DeleteHabitDialogViewModel : ViewModel() {

    private val repository = BetterRepository()

    private val _habit = MutableLiveData<Habit>()
    val habit: LiveData<Habit>
        get() = _habit

    val isDeleteHabit = MutableLiveData(Event(false))

    private val _isDialogClose = MutableLiveData(Event(false))
    val isDialogClose: LiveData<Event<Boolean>>
        get() = _isDialogClose


    fun getHabitById(habitId: Long) {
        viewModelScope.launch {
            _habit.postValue(repository.getHabitByID(habitId))
        }
    }

    fun onClickDeleteHabit(habit: Habit) {
        isDeleteHabit.postValue(Event(true))
        viewModelScope.launch {
            repository.deleteHabit(habit)
        }
    }

    fun cancelDialog() {
        _isDialogClose.postValue(Event(true))
    }

}
