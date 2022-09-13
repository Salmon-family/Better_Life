package com.karrar.betterlife.ui.home

import androidx.lifecycle.*
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.database.entity.HabitResult
import com.karrar.betterlife.data.repository.BetterRepository
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel : ViewModel() {
    private val repository = BetterRepository()

    private val _navigateAddHabit = MutableLiveData<Boolean>()
    val navigateAddHabit: LiveData<Boolean>
        get() = _navigateAddHabit

    val habits: LiveData<List<Habit>> = repository.getAllHabit().asLiveData()
    val checkedBtnObs = MutableLiveData<Int>()


    fun navigateToAddHabitDialog(){
        _navigateAddHabit.postValue(true)
    }

    fun setTodayHabit(habitID: Long) {
        viewModelScope.launch {
            val habit = repository.getHabitByID(habitID)
            habit?.let {
                repository.insertTodayHabit(
                    HabitResult(
                        id_habit = it.id,
                        point = it.point,
                        date = Date()
                    )
                )
            }
        }
    }
}