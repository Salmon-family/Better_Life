package com.karrar.betterlife.data.repository

import com.karrar.betterlife.BetterLifeApp
import com.karrar.betterlife.data.database.BetterLiveDatabase
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.database.entity.HabitResult

class BetterRepository {
    private val habitDao =
        BetterLiveDatabase.getInstanceByApplicationContext().habitDao()

    private val dailyDao =
        BetterLiveDatabase.getInstanceByApplicationContext().habitResultDao()

    suspend fun insertNewHabit(habit: Habit) = habitDao.insert(habit)

    suspend fun updateHabit(habit: Habit) = habitDao.update(habit)

    suspend fun getHabitByID(habitID: Long) = habitDao.getHabitByID(id = habitID)

    suspend fun getAllHabitByIDs(habitIds: List<Long>) = habitDao.getAllHabitByIDs(habitIds)

    fun getAllHabit() = habitDao.getAllHabit()

    /**
     * Daily Habits...
     * */
    suspend fun getTodayHabit(habitID: Long) = dailyDao.getTodayHabitResultByID(habitID)

    suspend fun insertTodayHabit(habit: HabitResult) = dailyDao.insert(habit)

    suspend fun deleteHabit(habit: HabitResult) = dailyDao.delete(habit)

    suspend fun isAnyHabitsInThisDay(day: Long) = dailyDao.isAnyHabitsByThisDay(day)
}