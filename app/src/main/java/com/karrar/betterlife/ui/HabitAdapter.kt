package com.karrar.betterlife.ui

import com.karrar.betterlife.R
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.ui.base.BaseAdapter
import com.karrar.betterlife.ui.base.BaseInteractionListener

class HabitAdapter(items:List<Habit>,listener:HabitInteractionListener):BaseAdapter<Habit>(items, listener){
    override val layoutID: Int = R.layout.item_edit_habit
}
interface HabitInteractionListener:BaseInteractionListener
//{fun onclick(habit:Habit)}