package com.karrar.betterlife.data.database

import androidx.room.*
import com.karrar.betterlife.data.database.entity.HabitResult

@Dao
interface HabitResultDao {

    @Insert
    suspend fun insert(habitResult: HabitResult)

    @Update
    suspend fun update(habitResult: HabitResult)

    @Delete
    suspend fun delete(habitResult: HabitResult)

    @Query("SELECT * FROM RESULT_TABLE")
    suspend fun getAllHabitResult(): List<HabitResult>
}