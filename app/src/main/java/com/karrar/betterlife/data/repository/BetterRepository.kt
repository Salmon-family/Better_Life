package com.karrar.betterlife.data.repository

import com.karrar.betterlife.data.database.BetterLiveDatabase
import com.karrar.betterlife.data.database.entity.DailyHabits
import com.karrar.betterlife.data.database.entity.Habit

class BetterRepository {
    private val habitDao =
        BetterLiveDatabase.getInstanceByApplicationContext().habitDao()

    suspend fun insertNewHabit(habit: Habit) = habitDao.insert(habit)

    suspend fun insertHabits(habits:List<Habit>) = habitDao.insertHabits(habits)

    suspend fun updateHabit(habit: Habit) = habitDao.update(habit)

    fun getAllHabit() = habitDao.getAllHabit()


    /**
     * Daily ..
     * */

    suspend fun isAnyHabitsInThisDay(day: Long)= habitDao.isAnyHabitsInThisDay(day)

    suspend fun insertAllHabitsPerDay(todayHabits: List<DailyHabits>) = habitDao.insert(todayHabits)

    suspend fun getAllHabitPerDay(day: Long) = habitDao.getAllHabitPerDay(day)

    suspend fun getPointsInRange(startDate: Long, endDate: Long) = habitDao.getPointsInRange(startDate, endDate)

    suspend fun getPointDuringYearWithDate(maxYear:Long) = habitDao.getPointDuringYearWithDate(maxYear)

    suspend fun insertTodayHabits(todayHabits: DailyHabits) = habitDao.insert(todayHabits)

}