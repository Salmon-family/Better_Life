package com.karrar.betterlife.ui.tasks

import com.karrar.betterlife.R
import com.karrar.betterlife.data.database.entity.Task
import com.karrar.betterlife.ui.tasks.temp.BaseAdapter

class TasksAdapter(var list: List<Task>, listener: TasksInteractionListener) :
    BaseAdapter<Task>(list, listener) {
    override val layoutID = R.layout.item_task

    override fun areContentsSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.isChecked == newItem.isChecked
                && oldItem.note == newItem.note
    }
}