package com.karrar.betterlife.ui.edit

import androidx.lifecycle.*
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.repository.BetterRepository
import com.karrar.betterlife.ui.HabitInteractionListener
import com.karrar.betterlife.util.Event
import kotlinx.coroutines.launch

class EditHabitViewModel : ViewModel(), HabitInteractionListener {
    private val repository = BetterRepository()
    
    val habits: LiveData<List<Habit>> = repository.getAllHabit().asLiveData()

    private val _navigateTOEditHabitDialog = MutableLiveData(Event(false))
    val navigateTOEditHabitDialog: LiveData<Event<Boolean>>
        get() = _navigateTOEditHabitDialog
    
    override fun onDeleteHabit(habit: Habit) {
        viewModelScope.launch {
            repository.deleteHabit(habit)
        }
    }

    override fun onEditHabit(habit: Habit) {
        _navigateTOEditHabitDialog.postValue(Event(true))
    }

}


