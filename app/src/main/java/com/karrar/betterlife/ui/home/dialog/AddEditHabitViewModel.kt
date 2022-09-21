package com.karrar.betterlife.ui.home.dialog

import androidx.lifecycle.*
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.repository.BetterRepository
import com.karrar.betterlife.util.DialogCancelClickEvent
import com.karrar.betterlife.util.DialogState
import com.karrar.betterlife.util.Event
import kotlinx.coroutines.launch

class AddEditHabitViewModel : ViewModel() {

    private val repository = BetterRepository()

    val habitName = MutableLiveData("")

    val habitPoints = MutableLiveData(0)

    private val _clickEvent = MutableLiveData<DialogState>()

    private val _dialogTitle = MutableLiveData("")
    val dialogTitle: LiveData<String>
        get() = _dialogTitle

    private val _dialogButtonText = MutableLiveData("")
    val dialogButtonText: LiveData<String>
        get() = _dialogButtonText

    private val _cancelClickEvent = MutableLiveData<Event<DialogCancelClickEvent>>()
    val cancelClickEvent: LiveData<Event<DialogCancelClickEvent>>
        get() = _cancelClickEvent

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

    fun initializeDialogDependOnValueFromNavigationArgs(habitId: Long?) {
        if (habitId == -1L) {
            initializeDialogToAddHabit()
        } else {
            initializeDialogToUpdateHabit(habitId!!)
        }
    }

    private fun initializeDialogToAddHabit() {
        _clickEvent.postValue(DialogState.ADD)
        habitName.postValue("")
        habitPoints.postValue(0)
        _dialogTitle.postValue("New Habit")
        _dialogButtonText.postValue("Add")
    }

    private fun initializeDialogToUpdateHabit(habitId: Long) {
        _clickEvent.postValue(DialogState.UPDATE)
        viewModelScope.launch {
            val habit = repository.getHabitByID(habitId)
            _habit.postValue(habit)
            habitName.postValue(habit?.name)
            habitPoints.postValue(habit?.point)
            _dialogTitle.postValue("Update Habit")
            _dialogButtonText.postValue("Update")
        }
    }

    fun applyButtonClick() {
        _clickEvent.value?.let {
            when (it) {
                DialogState.UPDATE -> {
                    updateHabit()
                }
                DialogState.ADD -> {
                    addNewHabit()
                }
            }
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
            _cancelClickEvent.postValue(Event(DialogCancelClickEvent.OnHabitAddClick))
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
            _cancelClickEvent.postValue(Event(DialogCancelClickEvent.OnHabitUpdateClick))
        }
    }

    fun cancelDialog() {
        _cancelClickEvent.postValue(Event(DialogCancelClickEvent.OnDialogCancelClick))

    }
}