package com.karrar.betterlife.data.repository

import com.karrar.betterlife.data.database.BetterLiveDatabase
import com.karrar.betterlife.data.database.entity.DailyHabits
import com.karrar.betterlife.data.database.entity.Day
import com.karrar.betterlife.data.database.entity.DayWithHabits
import com.karrar.betterlife.data.database.entity.Habit

class BetterRepository {
    private val habitDao =
        BetterLiveDatabase.getInstanceByApplicationContext().habitDao()


    suspend fun insertNewHabit(habit: Habit) = habitDao.insert(habit)

    suspend fun updateHabit(habit: Habit) = habitDao.update(habit)

    fun getAllHabit() = habitDao.getAllHabit()

    suspend fun getHabitsByDay(dayid: Long) = habitDao.getHabitsByDay(dayid)

    suspend fun getHabitsByRangOfDays(first: Long, second: Long) =
        habitDao.getHabitsByRangOfDays(first, second)

    /**
     * Daily ..
     * */
    suspend fun insertAllHabitsPerDay(todayHabits: List<DailyHabits>)= habitDao.insert(todayHabits)

    suspend fun getdayID(today: Long) = habitDao.getDay(today)

    suspend fun insertToday(today: Day) = habitDao.insert(today)

    suspend fun insertTodayHabits(todayHabits: DailyHabits) = habitDao.insert(todayHabits)

    suspend fun isAnyHabitsInThisDay(today: Long): List<DayWithHabits>? {
        val day = getdayID(today)
        day?.let {
            return getHabitsByDay(day.dayID)
        }
        return null
    }

}