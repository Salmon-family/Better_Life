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

    @Query("SELECT * FROM RESULT_TABLE WHERE date(date / 1000,'unixepoch') = date(:day / 1000,'unixepoch')")
    suspend fun isAnyHabitsByThisDay(day: Long): List<HabitResult>

    @Query("SELECT date, total(point) FROM RESULT_TABLE GROUP BY date ORDER BY date ASC;")
    suspend fun selectDate(day: Long) : Int

}