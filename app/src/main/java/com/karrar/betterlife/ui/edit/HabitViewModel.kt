package com.karrar.betterlife.ui.edit

import androidx.lifecycle.*
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.repository.BetterRepository
import com.karrar.betterlife.ui.HabitInteractionListener
import com.karrar.betterlife.util.HabitFragmentClickEvent

class HabitViewModel : ViewModel(), HabitInteractionListener {
    private val repository = BetterRepository()

    val habits: LiveData<List<Habit>> = repository.getAllHabit().asLiveData()

    private val _onClickEvent = MutableLiveData<HabitFragmentClickEvent>()
    val onClickEvent: LiveData<HabitFragmentClickEvent>
        get() = _onClickEvent

    override fun onClickDeleteHabit(habitId: Long) {
        _onClickEvent.postValue(HabitFragmentClickEvent.OnDeleteHabit(habitId))
    }

    override fun onClickEditHabit(habitId: Long) {
        _onClickEvent.postValue(HabitFragmentClickEvent.OnEditHabit(habitId))
    }

    fun onClickAddDialog(){
        _onClickEvent.postValue(HabitFragmentClickEvent.OnAddHabit)
    }
}


