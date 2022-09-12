package com.karrar.betterlife.data.database

import androidx.room.*
import com.karrar.betterlife.data.Habit

@Dao
interface HabitDao {

    @Insert
    fun insert(betterLive: Habit)

    @Update
    fun update(betterLive: Habit)

    @Delete
    fun delete(betterLive: Habit)

    @Query("SELECT * FROM BETTER_LIFE_TABLE")
    fun getAllBetterLife(): List<Habit>

}