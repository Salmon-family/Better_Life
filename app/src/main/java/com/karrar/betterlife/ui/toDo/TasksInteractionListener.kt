package com.karrar.betterlife.ui.toDo

import com.karrar.betterlife.data.database.entity.Task
import com.karrar.betterlife.ui.toDo.temp.BaseInteractionListener

interface TasksInteractionListener : BaseInteractionListener {
    fun onClickDelete(task: Task)
    fun onClickCheck(task: Task)
}