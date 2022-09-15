package com.karrar.betterlife.ui.edit.editHabitDialog

import androidx.lifecycle.*
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.repository.BetterRepository
import com.karrar.betterlife.util.Event
import kotlinx.coroutines.launch

class EditHabitViewModel : ViewModel() {

    private val repository = BetterRepository()

     val name = MutableLiveData<String>()
     val points = MutableLiveData<String>()

    val isEditHabit = MutableLiveData(Event(false))

    private val _isDialogClose = MutableLiveData(Event(false))
    val isDialogClose: LiveData<Event<Boolean>>
        get() = _isDialogClose

     val editHabitValidation = MediatorLiveData<Boolean>().apply {
        addSource(name, this@EditHabitViewModel::checkValidation)
        addSource(points, this@EditHabitViewModel::checkValidation)
    }

    private fun checkValidation(value: String) {
        if (name.value?.isNotEmpty() == true && points.value?.isNotEmpty() == true) {
            editHabitValidation.postValue(true)
        } else {
            editHabitValidation.postValue(false)
        }
    }

    fun onApplyHabit() {
        isEditHabit.postValue(Event(true))
        viewModelScope.launch {
            repository.updateHabit(
                Habit(
                    name = name.value.toString(),
                    point = points.value.toString().toInt()
                )
            )
        }
    }

    fun closeDialog(){
        _isDialogClose.postValue(Event(true))
    }

}