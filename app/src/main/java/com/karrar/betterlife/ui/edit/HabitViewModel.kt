package com.karrar.betterlife.ui.edit

import androidx.lifecycle.*
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.repository.BetterRepository
import com.karrar.betterlife.ui.HabitInteractionListener
import com.karrar.betterlife.util.Event
import kotlinx.coroutines.launch

class HabitViewModel : ViewModel(), HabitInteractionListener {
    private val repository = BetterRepository()

    val habits: LiveData<List<Habit>> = repository.getAllHabit().asLiveData()

    private val _navigateTOEditHabitDialog = MutableLiveData<Event<Int>>()
    val navigateTOEditHabitDialog: LiveData<Event<Int>>
        get() = _navigateTOEditHabitDialog
    
    override fun onDeleteHabit(habit: Habit) {
        viewModelScope.launch {
            repository.deleteHabit(habit)
        }
    }

    override fun onEditHabit(habitId: Int) {
        _navigateTOEditHabitDialog.postValue(Event(habitId))
    }

}


