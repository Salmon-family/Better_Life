package com.karrar.betterlife.data.database

import androidx.room.*
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.database.entity.HabitResult
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {

    @Insert
    suspend fun insert(habit: Habit)

    @Update
    suspend fun update(habit: Habit)

    @Delete
    suspend fun delete(habit: Habit)

    @Query("SELECT * FROM HABIT_TABLE WHERE id=:id ")
    suspend fun getHabitByID(id: Long): Habit?

    @Query("SELECT * FROM HABIT_TABLE ORDER BY point DESC")
    fun getAllHabit(): Flow<List<Habit>>

    //you can use this too, for delete note by id.
    @Query("DELETE FROM HABIT_TABLE WHERE id = :id")
    suspend fun deleteNoteById(id: Long)

    @Query("SELECT * FROM HABIT_TABLE WHERE id IN (:habitIds)")
    suspend fun getAllHabitByIDs(habitIds: List<Long>): List<Habit>

    @Query("SELECT * FROM RESULT_TABLE")
    fun getAllResultHabit(): Flow<List<HabitResult>>

}