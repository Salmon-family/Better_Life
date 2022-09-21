package com.karrar.betterlife.ui.tasks

import androidx.lifecycle.*
import com.karrar.betterlife.data.database.entity.Task
import com.karrar.betterlife.data.repository.BetterRepository
import com.karrar.betterlife.util.Event
import kotlinx.coroutines.launch
import java.util.*

class TasksViewModel : ViewModel(), TasksInteractionListener {

    private val repository = BetterRepository()

    val taskText = MutableLiveData<String>()

    val tasks: LiveData<List<Task>> = repository.getAllTasks().asLiveData()

    private val _onCLickFloatingButtonEvent = MutableLiveData(Event(false))
    val onCLickFloatingButtonEvent: LiveData<Event<Boolean>>
        get() = _onCLickFloatingButtonEvent

    private val _onCLickCancelEvent = MutableLiveData(Event(false))
    val onClickCancelEvent: LiveData<Event<Boolean>>
        get() = _onCLickCancelEvent

    private val _onCLickAddEvent = MutableLiveData(Event(false))
    val onClickAddEvent: LiveData<Event<Boolean>>
        get() = _onCLickAddEvent

    private val _isEmptyList = MutableLiveData(false)
    val isEmptyList: LiveData<Boolean>
        get() = _isEmptyList


    fun checkIfEmptyList() {
        viewModelScope.launch {
            if (tasks.value?.isEmpty() == true) {
                _isEmptyList.postValue(true)
            } else {
                _isEmptyList.postValue(false)
            }
        }
    }


    override fun onClickDelete(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }


    override fun onClickCheck(task: Task) {
        viewModelScope.launch {
            task.isChecked = !task.isChecked
            repository.update(task)
        }
    }


    override fun onTextChangedListener(task: Task) {
        viewModelScope.launch {
            repository.update(task)
        }
    }


    fun onClickFloatingButton() {
        _onCLickFloatingButtonEvent.postValue(Event(true))
    }

    fun onClickAdd() {
        addTask()
        _onCLickAddEvent.postValue(Event(true))
    }

    private fun addTask() {
        viewModelScope.launch {
            taskText.value?.let { text ->
                repository.insertNewTask(Task(0, text, Date(), false))
            }
        }
    }

    fun onClickCancel() {
        _onCLickCancelEvent.postValue(Event(true))
    }

}