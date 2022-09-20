package com.karrar.betterlife.ui.edit.editHabitDialog

import androidx.lifecycle.*
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.repository.BetterRepository
import com.karrar.betterlife.util.Event
import kotlinx.coroutines.launch

class EditHabitDialogViewModel : ViewModel() {

    private val repository = BetterRepository()

    var name = MutableLiveData<String>("")
    var points= MutableLiveData<String>("")

    private val _habit = MutableLiveData<Habit>()
    val habit: LiveData<Habit>
        get() = _habit

    val isEditHabit = MutableLiveData(Event(false))

    private val _isDialogClose = MutableLiveData(Event(false))
    val isDialogClose: LiveData<Event<Boolean>>
        get() = _isDialogClose


    fun getHabitById(habitId:Long){
        viewModelScope.launch {
            _habit.postValue(repository.getHabitByID(habitId))
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

    fun onClickApplyHabit(habit: Habit){
        isEditHabit.postValue(Event(true))
        viewModelScope.launch {
            repository.updateHabit(
                habit.copy(name = name.value!!, point = points.value?.toInt()!!)
            )
        }
    }


    fun cancelDialog(){
        _isDialogClose.postValue(Event(true))
    }

}