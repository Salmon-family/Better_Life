package com.karrar.betterlife.ui.edit.editHabitDialog

import androidx.lifecycle.*
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.repository.BetterRepository
import com.karrar.betterlife.util.Event
import kotlinx.coroutines.launch

class EditHabitDialogViewModel : ViewModel() {

    private val repository = BetterRepository()

    var name = MutableLiveData<String>()
    var points= MutableLiveData<String>()

    private val _habit = MutableLiveData<Habit?>()
    val habit: LiveData<Habit?>
        get() = _habit

    val isEditHabit = MutableLiveData(Event(false))

    private val _isDialogClose = MutableLiveData(Event(false))
    val isDialogClose: LiveData<Event<Boolean>>
        get() = _isDialogClose


    fun getHabitById(habitId:Long){
        viewModelScope.launch {
            val habit = repository.getHabitByID(habitId)
            _habit.postValue(habit)
            name.postValue(habit?.name)
            points.postValue(habit?.point.toString())

        }
    }

    val editHabitValidation = MediatorLiveData<Boolean>().apply {
        addSource(name, this@EditHabitDialogViewModel::checkValidation)
        addSource(points, this@EditHabitDialogViewModel::checkValidation)
    }

    private fun checkValidation(value: String) {
        if (name.value?.isNotEmpty() == true && points.value?.isNotEmpty() == true) {
            editHabitValidation.postValue(true)
        } else {
            editHabitValidation.postValue(false)
        }
    }

    fun onClickApplyHabit(){
        isEditHabit.postValue(Event(true))
        viewModelScope.launch {
            habit.value?.let {
                repository.updateHabit(
                    it.copy(name = name.value.toString(), point = points.value?.toInt() ?: 0)
                )
            }
        }
    }


    fun cancelDialog(){
        _isDialogClose.postValue(Event(true))
    }

}