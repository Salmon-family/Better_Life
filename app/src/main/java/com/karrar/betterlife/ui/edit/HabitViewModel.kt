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

    private val _navigateTOEditHabitDialog = MutableLiveData<Event<Long>>()
    val navigateTOEditHabitDialog: LiveData<Event<Long>>
        get() = _navigateTOEditHabitDialog


    override fun onClickDeleteHabit(habit: Habit) {
        viewModelScope.launch {
            repository.deleteHabit(habit)
        }
    }

    override fun onClickEditHabit(habit:Long) {
        _navigateTOEditHabitDialog.postValue(Event(habit))
    }

}


