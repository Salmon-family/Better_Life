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

    suspend fun isAnyHabitsInThisDay(day: Long) = dailyDao.isAnyHabitsByThisDay(day)

    suspend fun getTotalHabitPointInResult(fromDate: Long, toDate: Long) = dailyDao.getTotalHabitPointInResult(fromDate, toDate)

    fun getAllHabit() = habitDao.getAllHabit()

    fun getAllResultHabit() = habitDao.getAllResultHabit()

    suspend fun getHabitByID(habitID: Long) = habitDao.getHabitByID(id = habitID)
}