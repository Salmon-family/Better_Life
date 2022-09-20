package com.karrar.betterlife.ui.toDo

import com.karrar.betterlife.R
import com.karrar.betterlife.data.database.entity.Task
import com.karrar.betterlife.ui.toDo.temp.BaseAdapter

class TasksAdapter(var list: List<Task>, listener: TasksInteractionListener) :
    BaseAdapter<Task>(list, listener) {
    override val layoutID = R.layout.item_task
}