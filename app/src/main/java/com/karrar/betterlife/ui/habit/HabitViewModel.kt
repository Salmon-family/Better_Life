package com.karrar.betterlife.ui.habit

import androidx.lifecycle.*
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.database.repositories.HabitRepository
import kotlinx.coroutines.launch


class HabitViewModel: ViewModel() {

   private val repository = HabitRepository()

    val newHabitText = MutableLiveData<String>()

    val habits: LiveData<List<Habit>> = repository.getAllHabit().asLiveData()


    fun deleteHabit(){
        viewModelScope.launch {
            newHabitText.value?.let {
                repository.deleteHabit(Habit())
            }
        }

        }

    fun updateHabit(){
        viewModelScope.launch {
            newHabitText.value?.let {
                repository.updateHabit(Habit(332,"sport",0))
            }
        }

    }
    //        private val _habits = MutableLiveData<List<Habit>>()
//    init {
//        loadHabits()
//    }

//    private fun loadHabits() {
//        viewModelScope.launch {
//            repository.getAllHabit().collect{
//                _habits.postValue(it)
//            }
//        }
//    }
    }
