package com.karrar.betterlife.ui.home

import com.karrar.betterlife.R
import com.karrar.betterlife.data.DailyHabit
import com.karrar.betterlife.ui.base.BaseAdapter

class HabitDailyAdapter(items: List<DailyHabit>) : BaseAdapter<DailyHabit>(items, null) {
    override val layoutID: Int = R.layout.item_day
}