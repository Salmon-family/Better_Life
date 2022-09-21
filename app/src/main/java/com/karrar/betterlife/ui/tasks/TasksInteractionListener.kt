package com.karrar.betterlife.ui.tasks

import com.karrar.betterlife.data.database.entity.Task
import com.karrar.betterlife.ui.tasks.temp.BaseInteractionListener

interface TasksInteractionListener : BaseInteractionListener {

    fun onClickDelete(task: Task)
    fun onClickCheck(task: Task)
    fun onTextChangedListener(task: Task)

}