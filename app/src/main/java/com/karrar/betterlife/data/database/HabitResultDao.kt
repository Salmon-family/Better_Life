package com.karrar.betterlife.data.database

import androidx.room.*
import com.karrar.betterlife.data.database.entity.HabitResult
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface HabitResultDao {

    @Insert
    suspend fun insert(habitResult: HabitResult)

    @Update
    suspend fun update(habitResult: HabitResult)

    @Delete
    suspend fun delete(habitResult: HabitResult)

    @Query("SELECT * FROM RESULT_TABLE WHERE id_habit == :habitID")
    suspend fun getTodayHabitResultByID(habitID: Long): HabitResult

    @Query("SELECT * FROM RESULT_TABLE")
    suspend fun getAllHabitResult(): List<HabitResult>

    @Query("SELECT * FROM RESULT_TABLE WHERE date == :day")
    suspend fun isAnyHabitsByThisDay(day: Long): List<HabitResult>


    @Query("SELECT SUM(point) FROM RESULT_TABLE WHERE date BETWEEN :fromDate AND :toDate ")
    suspend fun getTotalHabitPointsInRange(fromDate : Long, toDate: Long): Int
}
