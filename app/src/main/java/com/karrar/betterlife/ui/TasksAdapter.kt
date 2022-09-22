package com.karrar.betterlife.ui

import com.karrar.betterlife.R
import com.karrar.betterlife.data.database.entity.Task
import com.karrar.betterlife.ui.base.BaseAdapter
import com.karrar.betterlife.ui.base.BaseInteractionListener

class TasksAdapter(var list: List<Task>, listener: TasksInteractionListener) :
    BaseAdapter<Task>(list, listener) {
    override val layoutID = R.layout.item_task

    override fun areContentsSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.isChecked == newItem.isChecked
                && oldItem.note == newItem.note
    }
}

interface TasksInteractionListener : BaseInteractionListener {

    fun onClickDelete(task: Task)
    fun onClickCheck(task: Task)
    fun onTextChangedListener(task: Task)

}