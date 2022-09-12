package com.karrar.betterlife.data.database

import androidx.room.*
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.database.entity.HabitResult

@Dao
interface BetterLiveDao {

    @Insert
    suspend fun insertHabit(habit: Habit)

    @Insert
    suspend fun insertHabitResult(HabitResult: HabitResult)


    @Insert
    suspend fun updateHabit(habit: Habit)

    @Insert
    suspend fun updateHabitResult(HabitResult: HabitResult)


    @Delete
    suspend fun deleteHabit(habit: Habit)

    @Delete
    suspend fun deleteHabitResult(HabitResult: HabitResult)


    @Query("SELECT * FROM HABIT_TABLE")
    suspend fun getAllHabit(habit: Habit)

    @Query("SELECT * FROM RESULT_TABLE")
    suspend fun getAllHabitResult(HabitResult: HabitResult)


}