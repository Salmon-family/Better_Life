package com.karrar.betterlife.data.database

import androidx.room.*
import com.karrar.betterlife.data.Habit

@Dao
interface HabitDao {

    @Insert
    fun insert(habit: Habit)

    @Update
    fun update(habit: Habit)

    @Delete
    fun delete(habit: Habit)

    @Query("SELECT * FROM HABIT_TABLE")
    fun getAllBetterLife(): List<Habit>

}