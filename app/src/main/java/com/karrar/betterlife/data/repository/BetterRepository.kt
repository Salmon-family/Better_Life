package com.karrar.betterlife.data.repository

import com.karrar.betterlife.data.database.BetterLiveDatabase
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.database.entity.HabitResult
import com.karrar.betterlife.data.database.entity.Task

class BetterRepository {

    private val habitDao = BetterLiveDatabase.getInstanceByApplicationContext().habitDao()

    private val dailyDao = BetterLiveDatabase.getInstanceByApplicationContext().habitResultDao()

    private val taskDao = BetterLiveDatabase.getInstanceByApplicationContext().taskDao()


    suspend fun insertNewHabit(habit: Habit) = habitDao.insert(habit)

    suspend fun updateHabit(habit: Habit) = habitDao.update(habit)

    suspend fun getTotalPointsOfDay(day: Long) = dailyDao.getTotalPointsOfDay(day)

    suspend fun getTotalHabitPointInResult(fromDate: Long, toDate: Long) =
        dailyDao.getTotalHabitPointInResult(fromDate, toDate)

    fun getAllHabit() = habitDao.getAllHabit()

    fun getAllResultHabit() = habitDao.getAllResultHabit()

    suspend fun getHabitByID(habitID: Long) = habitDao.getHabitByID(id = habitID)

    suspend fun getAllHabitByIDs(habitIds: List<Long>) = habitDao.getAllHabitByIDs(habitIds)

    /**
     * Daily Habits...
     * */
    suspend fun getTodayHabit(habitID: Long) = dailyDao.getTodayHabitResultByID(habitID)

    suspend fun insertTodayHabit(habit: HabitResult) = dailyDao.insert(habit)

    suspend fun deleteHabit(habit: HabitResult) = dailyDao.delete(habit)

    suspend fun isAnyHabitsInThisDay(day: Long) = dailyDao.isAnyHabitsByThisDay(day)

    suspend fun getTotalHabitPointsInRange(fromDate: Long, toDate: Long) =
        dailyDao.getTotalHabitPointsInRange(fromDate, toDate)

    /**
     * Task
     */

    suspend fun insertNewTask(task: Task) = taskDao.insert(task)

    suspend fun deleteTask(task: Task) = taskDao.delete(task)

    suspend fun update(task: Task) = taskDao.update(task)

    fun getAllTasks() = taskDao.getAllTasks()

}