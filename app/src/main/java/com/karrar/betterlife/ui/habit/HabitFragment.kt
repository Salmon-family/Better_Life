package com.karrar.betterlife.ui.habit

import com.karrar.betterlife.R
import com.karrar.betterlife.data.database.BetterLiveDatabase
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.databinding.FragmentHabitListBinding
import com.karrar.betterlife.databinding.FragmentHomeBinding
import com.karrar.betterlife.ui.base.BaseFragment
import com.karrar.betterlife.ui.home.HomeViewModel

class HabitFragment: BaseFragment<FragmentHabitListBinding, HabitViewModel>() {
    override val layoutIdFragment = R.layout.fragment_habit_list
    override val viewModelClass = HabitViewModel::class.java

    override fun setup() {
       // val database = BetterLiveDatabase.getInstance(activity?.applicationContext!!)


    }

}