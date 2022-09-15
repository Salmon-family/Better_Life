package com.karrar.betterlife.data.database

import androidx.room.*
import com.karrar.betterlife.data.database.entity.HabitResult
import kotlin.collections.ArrayList

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

    @Query("SELECT * FROM RESULT_TABLE WHERE date == :day")
    suspend fun isAnyHabitsByThisDay(day: Long): List<HabitResult>

    @Query("SELECT SUM(point) FROM RESULT_TABLE GROUP BY date BETWEEN :fromDate AND :toDate")
    suspend fun getTotalHabitPointInResult(fromDate: Long, toDate: Long): Int

    @Query("SELECT COALESCE(SUM(point),0) FROM RESULT_TABLE WHERE date(date / 1000,'unixepoch') = date(:date / 1000,'unixepoch')") /*date(date / 1000,'unixepoch') = date(:date / 1000,'unixepoch')*/
    suspend fun getTotalPointsOfDay(date: Long): Int
}