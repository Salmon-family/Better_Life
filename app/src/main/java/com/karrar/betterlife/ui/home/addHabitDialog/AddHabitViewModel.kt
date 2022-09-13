package com.karrar.betterlife.ui.home.addHabitDialog

import androidx.lifecycle.*
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.repository.BetterRepository
import com.karrar.betterlife.util.Event
import kotlinx.coroutines.launch


class AddHabitViewModel : ViewModel() {
    private val repository = BetterRepository()

    val habitName = MutableLiveData<String>()
    val habitPoints = MutableLiveData<String>()
    val isAddHabit = MutableLiveData(Event(false))

    val addHabitValidation = MediatorLiveData<Boolean>().apply {
        addSource(habitName, this@AddHabitViewModel::checkValidation)
        addSource(habitPoints, this@AddHabitViewModel::checkValidation)
    }

    private fun checkValidation(value: String) {
        if (habitName.value?.isNotEmpty() == true && habitPoints.value?.isNotEmpty() == true) {
            addHabitValidation.postValue(true)
        } else {
            addHabitValidation.postValue(false)
        }
    }


    fun addNewHabit() {
        isAddHabit.postValue(Event(true))
        viewModelScope.launch {
            repository.insertNewHabit(
                Habit(
                    name = habitName.value.toString(),
                    point = 100
                )
            )
        }
    }

}