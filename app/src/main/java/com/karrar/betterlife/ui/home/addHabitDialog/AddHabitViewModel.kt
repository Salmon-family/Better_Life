package com.karrar.betterlife.ui.home.addHabitDialog

import androidx.lifecycle.*
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.repository.BetterRepository
import com.karrar.betterlife.util.Event
import kotlinx.coroutines.launch


class AddHabitViewModel : ViewModel() {
    private val repository = BetterRepository()

    val habitName = MutableLiveData<String>()
    val habitNumber = MutableLiveData<Int>()
    val isAddHabit = MutableLiveData(Event(false))

    val validation = MediatorLiveData<Boolean>().apply {
        addSource(habitName, this@AddHabitViewModel::checkIsValidation)
    }

    private fun checkIsValidation(value: String) {
        if (habitName.value.isNullOrEmpty() && habitNumber.value == 0) {
            validation.postValue(true)
        } else {
            validation.postValue(false)
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