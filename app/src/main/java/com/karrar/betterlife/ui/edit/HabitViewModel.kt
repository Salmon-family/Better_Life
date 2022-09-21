package com.karrar.betterlife.ui.edit

import androidx.lifecycle.*
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.repository.BetterRepository
import com.karrar.betterlife.ui.HabitInteractionListener
import com.karrar.betterlife.util.HabitFragmentClickEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HabitViewModel : ViewModel(), HabitInteractionListener {
    private val repository = BetterRepository()

    val habits: LiveData<List<Habit>> = repository.getAllHabit().asLiveData()

    private val _onClickEvent = Channel<HabitFragmentClickEvent>()
    val onClickEvent = _onClickEvent.receiveAsFlow()

    override fun onClickDeleteHabit(habitId: Long) {
        viewModelScope.launch {
            _onClickEvent.send(HabitFragmentClickEvent.OnDeleteHabit(habitId))
        }
    }

    override fun onClickEditHabit(habitId: Long) {
        viewModelScope.launch {
            _onClickEvent.send(HabitFragmentClickEvent.OnEditHabit(habitId))
        }
    }

    fun onClickAddDialog(){
        viewModelScope.launch {
            _onClickEvent.send(HabitFragmentClickEvent.OnAddHabit)
        }
    }
}


