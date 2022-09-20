package com.karrar.betterlife.ui.toDo

import androidx.lifecycle.*
import com.karrar.betterlife.data.database.entity.Task
import com.karrar.betterlife.data.repository.BetterRepository
import kotlinx.coroutines.launch
import java.util.*

class TasksViewModel : ViewModel(), TasksInteractionListener {

    private val repository = BetterRepository()

    val taskText = MutableLiveData<String>()

    val tasks: LiveData<List<Task>> = repository.getAllTasks().asLiveData()


    fun addTask() {
        viewModelScope.launch {
            taskText.value?.let { text ->
                repository.insertNewTask(Task(0, text, Date(), false))
                taskText.postValue("")
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

}