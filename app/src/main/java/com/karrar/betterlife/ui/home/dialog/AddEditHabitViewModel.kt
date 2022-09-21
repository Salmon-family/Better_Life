package com.karrar.betterlife.ui.home.dialog

import androidx.lifecycle.*
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.repository.BetterRepository
import com.karrar.betterlife.util.DialogState
import com.karrar.betterlife.util.Event
import kotlinx.coroutines.launch

class AddEditHabitViewModel : ViewModel() {

    private val repository = BetterRepository()

    val habitName = MutableLiveData("")
    val habitPoints = MutableLiveData(0)

    private val _dialogTitle = MutableLiveData("")
    val dialogTitle: LiveData<String>
        get() = _dialogTitle

    private val _dialogButtonText = MutableLiveData("")
    val dialogButtonText: LiveData<String>
        get() = _dialogButtonText

    private val _clickEvent = MutableLiveData<DialogState>()


    private val _isDialogCancel = MutableLiveData(Event(false))
    val isDialogCancel: LiveData<Event<Boolean>>
        get() = _isDialogCancel

    private val _isHabitAdd = MutableLiveData(Event(false))
    val isHabitAdd: LiveData<Event<Boolean>>
        get() = _isHabitAdd

    private val _isHabitUpdate = MutableLiveData(Event(false))
    val isHabitUpdate: LiveData<Event<Boolean>>
        get() = _isHabitUpdate

    private val _habit = MutableLiveData<Habit?>()
    val habit: LiveData<Habit?>
        get() = _habit

    val addHabitValidation = MediatorLiveData<Boolean>().apply {
        addSource(habitName, this@AddEditHabitViewModel::checkValidation)
        addSource(habitPoints, this@AddEditHabitViewModel::checkValidation)
    }

    private fun checkValidation(value: Any) {
        if (habitName.value?.isNotEmpty() == true && habitPoints.value != 0) {
            addHabitValidation.postValue(true)
        } else {
            addHabitValidation.postValue(false)
        }
    }

    fun getHabitById(habitId: String) {
        if (habitId.isEmpty()) {
            _clickEvent.postValue(DialogState.ADD)
            habitName.postValue("")
            habitPoints.postValue(0)
            _dialogTitle.postValue("New Habit")
            _dialogButtonText.postValue("Add")
        } else {
            _clickEvent.postValue(DialogState.UPDATE)
            viewModelScope.launch {
                val habit = repository.getHabitByID(habitId.toLong())
                _habit.postValue(habit)
                habitName.postValue(habit?.name)
                habitPoints.postValue(habit?.point)
                _dialogTitle.postValue("Update Habit")
                _dialogButtonText.postValue("Update")
            }
        }
    }

     fun applyButtonClick() {
        when (_clickEvent.value) {
            DialogState.UPDATE -> {
                updateHabit()
                _isHabitUpdate.postValue(Event(true))
            }
            DialogState.ADD -> {
                addNewHabit()
                _isHabitAdd.postValue(Event(true))
            }
            else -> {}
        }
    }

    private fun addNewHabit() {
        viewModelScope.launch {
            repository.insertNewHabit(
                Habit(
                    name = habitName.value.toString(),
                    point = habitPoints.value ?: 0
                )
            )
        }
    }

    private fun updateHabit() {
        viewModelScope.launch {
            habit.value?.let {
                repository.updateHabit(
                    it.copy(
                        name = habitName.value.toString(),
                        point = habitPoints.value?.toInt() ?: 0
                    )
                )
            }
        }
    }

    fun cancelDialog() {
        _isDialogCancel.postValue(Event(true))
    }
}