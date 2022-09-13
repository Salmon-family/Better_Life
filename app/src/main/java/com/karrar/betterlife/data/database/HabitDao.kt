package com.karrar.betterlife.data.database

import androidx.room.*
import com.karrar.betterlife.data.database.entity.Habit

@Dao
interface HabitDao {

    @Insert
    suspend fun insert(habit: Habit)

    @Update
    suspend fun update(habit: Habit)

    @Delete
    suspend fun delete(habit: Habit)

    @Query("SELECT * FROM HABIT_TABLE")
    suspend fun getAllHabit(): List<Habit>
}