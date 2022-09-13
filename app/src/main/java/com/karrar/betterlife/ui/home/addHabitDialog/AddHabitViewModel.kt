package com.karrar.betterlife.ui.home.addHabitDialog

import androidx.lifecycle.*
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.repository.BetterRepository
import com.karrar.betterlife.util.Event
import kotlinx.coroutines.launch


class AddHabitViewModel : ViewModel() {
    private val repository = BetterRepository()

    val habitName = MutableLiveData<String>()

    val isAddHabit = MutableLiveData(Event(false))


    fun addNewHabit() {
        if (!habitName.value.isNullOrEmpty()){
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
}