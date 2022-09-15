package com.karrar.betterlife.ui.home.addHabitDialog

import androidx.lifecycle.*
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.repository.BetterRepository
import com.karrar.betterlife.util.Constants
import com.karrar.betterlife.util.Constants.BAD
import com.karrar.betterlife.util.Constants.GOOD
import com.karrar.betterlife.util.Event
import kotlinx.coroutines.launch

class AddHabitViewModel : ViewModel() {
    private val repository = BetterRepository()

    val habitName = MutableLiveData<String>()
    val habitPoints = MutableLiveData(0)
    val isAddHabit = MutableLiveData(Event(false))
    val isCancel = MutableLiveData(Event(false))

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


    fun addNewHabit() {
        isAddHabit.postValue(Event(true))
        viewModelScope.launch {
            val point = habitPoints.value ?: 0

            repository.insertNewHabit(
                Habit(
                    name = habitName.value.toString(),
                    point = point
                )
            )
        }
    }

    fun cancelDialog() {
        isCancel.postValue(Event(true))
    }

}