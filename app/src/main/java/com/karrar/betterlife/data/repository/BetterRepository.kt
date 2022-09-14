package com.karrar.betterlife.data.repository

import com.karrar.betterlife.BetterLifeApp
import com.karrar.betterlife.data.database.BetterLiveDatabase
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.database.entity.HabitResult

class BetterRepository {
    private val habitDao =
        BetterLiveDatabase.getInstance(BetterLifeApp.applicationContext()).habitDao()

    private val dailyDao =
        BetterLiveDatabase.getInstance(BetterLifeApp.applicationContext()).habitResultDao()

    suspend fun insertTodayHabit(habit: HabitResult) = dailyDao.insert(habit)

    suspend fun insertNewHabit(habit: Habit) = habitDao.insert(habit)

    suspend fun updateHabit(habit: Habit) = habitDao.update(habit)

    suspend fun deleteHabit(habit: Habit) = habitDao.delete(habit)

    fun getAllHabit() = habitDao.getAllHabit()

    suspend fun getHabitByID(habitID: Long) = habitDao.getHabitByID(id = habitID)
}