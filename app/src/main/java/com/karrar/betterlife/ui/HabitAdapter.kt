package com.karrar.betterlife.ui

import com.karrar.betterlife.R
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.ui.base.BaseAdapter
import com.karrar.betterlife.ui.base.BaseInteractionListener

class HabitAdapter(items: List<Habit>, listener: HabitInteractionListener) :
    BaseAdapter<Habit>(items, listener) {
    override val layoutID: Int = R.layout.item_edit_habit

    override fun areContentsSame(oldItem: Habit, newItem: Habit): Boolean {
        return oldItem.name == newItem.name
                && oldItem.point == newItem.point
    }
}

interface HabitInteractionListener : BaseInteractionListener {

    fun onClickDeleteHabit(habitId: Long)
    fun onClickEditHabit(habitId: Long)

}