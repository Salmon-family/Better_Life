package com.karrar.betterlife.ui.edit

import androidx.lifecycle.*
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.database.entity.HabitResult
import com.karrar.betterlife.data.repository.BetterRepository
import com.karrar.betterlife.ui.HabitInteractionListener
import com.karrar.betterlife.util.Event
import kotlinx.coroutines.launch
import java.util.*

class EditHabitViewModel : ViewModel(){
    private val repository = BetterRepository()

        val newHabitText = MutableLiveData<String>()

    val habits: LiveData<List<Habit>> = repository.getAllHabit().asLiveData()

    private val _navigateTOEditHabit = MutableLiveData(Event(false))
    val navigateTOEditHabit: LiveData<Event<Boolean>>
        get() = _navigateTOEditHabit


    fun deleteDataHabit(habit: Habit) {
        viewModelScope.launch {
            newHabitText.value?.let {
                repository.deleteHabit(habit)
            }
        }
    }

        fun navigateTOEditHabitDialog() {
            _navigateTOEditHabit.postValue(Event(true))
        }
    }

//fun updateDataHabit(habit: Habit) {
//    viewModelScope.launch {
//        newHabitText.value?.let {
//            repository.updateHabit(habit)
//        }
//    }
