package com.karrar.betterlife.ui.home.addHabitDialog

import androidx.lifecycle.*
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.repository.BetterRepository
import com.karrar.betterlife.util.Event
import kotlinx.coroutines.launch

class AddHabitViewModel : ViewModel() {
    private val repository = BetterRepository()

    val habitName = MutableLiveData<String>()
    val habitPoints = MutableLiveData(0)

    private val _isAddHabit = MutableLiveData(Event(false))
    val isAddHabit: LiveData<Event<Boolean>>
        get() = _isAddHabit

    private val _isCancel = MutableLiveData(Event(false))
    val isCancel: LiveData<Event<Boolean>>
        get() = _isCancel

    val addHabitValidation = MediatorLiveData<Boolean>().apply {
        addSource(habitName, this@AddHabitViewModel::checkValidation)
        addSource(habitPoints, this@AddHabitViewModel::checkValidation)
    }

    private fun checkValidation(value: Any) {
        if (habitName.value?.isNotEmpty() == true && habitPoints.value != 0) {
            addHabitValidation.postValue(true)
        } else {
            addHabitValidation.postValue(false)
        }
    }


    fun addDialog() {
        _isAddHabit.postValue(Event(true))
        addNewHabit()
    }

    private fun addNewHabit() {
        viewModelScope.launch {
            repository.insertNewHabit(
                Habit(
                    name = habitName.value.toString(),
                    point = habitPoints.value ?: 0
                )
            )
        }
    }

    fun cancelDialog() {
        _isCancel.postValue(Event(true))
    }

}