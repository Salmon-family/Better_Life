package com.karrar.betterlife.util


sealed class HabitFragmentClickEvent{
    data class OnDeleteHabit(val long: Long):HabitFragmentClickEvent()
    data class OnEditHabit(val long: Long):HabitFragmentClickEvent()
    object OnAddHabit:HabitFragmentClickEvent()
}