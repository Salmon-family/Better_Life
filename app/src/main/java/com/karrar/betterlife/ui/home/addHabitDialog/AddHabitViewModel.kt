package com.karrar.betterlife.ui.home.addHabitDialog

import androidx.lifecycle.*
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.repository.BetterRepository
import kotlinx.coroutines.launch


class AddHabitViewModel : ViewModel() {
    private val repository = BetterRepository()

    val habitName = MutableLiveData<String?>()

    private val _isAddHabit = MutableLiveData<Boolean>()
    val isAddHabit: LiveData<Boolean>
        get() = _isAddHabit


    fun addNewHabit() {
        _isAddHabit.postValue(true)
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