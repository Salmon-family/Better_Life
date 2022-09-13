package com.karrar.betterlife.data.repository

import com.karrar.betterlife.BetterLifeApp
import com.karrar.betterlife.data.database.BetterLiveDatabase
import com.karrar.betterlife.data.database.entity.Habit

class BetterRepository {
    private val habitDao =
        BetterLiveDatabase.getInstance(BetterLifeApp.applicationContext()).habitDao()

    suspend fun insertNewHabit(habit: Habit) {
        habitDao.insert(habit)
    }

    fun getAllHabit() = habitDao.getAllHabit()

}