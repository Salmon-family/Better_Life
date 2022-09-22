package com.karrar.betterlife.data.repository

import com.karrar.betterlife.data.database.BetterLiveDatabase
import com.karrar.betterlife.data.database.entity.DailyHabits
import com.karrar.betterlife.data.database.entity.Habit

class BetterRepository {
    private val habitDao =
        BetterLiveDatabase.getInstanceByApplicationContext().habitDao()

    suspend fun insertNewHabit(habit: Habit) = habitDao.insert(habit)

    suspend fun insertHabits(habits: List<Habit>) = habitDao.insertHabits(habits)

    suspend fun updateHabit(habit: Habit) = habitDao.update(habit)

    suspend fun deleteHabit(habit: Habit) {
        habitDao.delete(habit)
        habitDao.deleteHabitIdFromDailyHabit(habit.habitID)
    }

    fun getAllHabit() = habitDao.getAllHabit()

    suspend fun isHabitAdded(name: String) = habitDao.isHabitAdded(name)

    suspend fun getHabitById(habitId: Long) = habitDao.getHabitById(habitId)

    suspend fun isAnyHabitsInThisDay(day: Long) = habitDao.isAnyHabitsInThisDay(day)

    suspend fun insertAllHabitsPerDay(todayHabits: List<DailyHabits>) = habitDao.insert(todayHabits)

    suspend fun getAllHabitPerDay(day: Long) = habitDao.getAllHabitPerDay(day)

    suspend fun getPointsWeekly(first: Long, end: Long) = habitDao.getPointsWeekly(first, end)

    suspend fun getPointsWeekly2(first: Long, second: Long) = habitDao.getPointsWeekly2(first, second)

    suspend fun getPointsInRange(startDate: Long, endDate: Long) =
        habitDao.getPointsInRange(startDate, endDate)

    suspend fun getPointDuringYearWithDate(maxYear: Long) =
        habitDao.getPointDuringYearWithDate(maxYear)

    suspend fun insertTodayHabits(todayHabits: DailyHabits) = habitDao.insert(todayHabits)

}