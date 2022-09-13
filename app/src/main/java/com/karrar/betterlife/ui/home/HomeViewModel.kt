package com.karrar.betterlife.ui.home

import androidx.lifecycle.*
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.repository.BetterRepository
import kotlinx.coroutines.launch
import java.lang.Math.random

class HomeViewModel : ViewModel() {

    private val repository = BetterRepository()

    val habits: LiveData<List<Habit>> = repository.getAllHabit().asLiveData()

    val checkedBtnObs = MutableLiveData<Int>()

    fun addFakeData() {
        viewModelScope.launch {
            val num = habits.value?.size?.plus(1)?:0
            repository.insertNewHabit(Habit(name = "category#$num", point = num))
        }
    }
}