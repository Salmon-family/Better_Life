package com.karrar.betterlife.data.database

import androidx.room.*
import com.karrar.betterlife.data.database.entity.DailyHabits
import com.karrar.betterlife.data.database.entity.Day
import com.karrar.betterlife.data.database.entity.DayWithHabits
import com.karrar.betterlife.data.database.entity.Habit
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {

    @Insert
    suspend fun insert(habit: Habit)

    @Insert
    suspend fun insert(day: Day)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(todayHabits: DailyHabits)

    @Update
    suspend fun update(habit: Habit)

    @Delete
    suspend fun delete(habit: Habit)

    @Query("SELECT * FROM habit")
    fun getAllHabit(): Flow<List<Habit>>

    @Transaction
    @Query("SELECT * FROM day WHERE dayID <= :day")
    suspend fun getHabitsByDay(day: Long): List<DayWithHabits>

    @Transaction
    @Query("SELECT * FROM day WHERE (dayID <= :second AND dayID >= :first)")
    suspend fun getHabitsByRangOfDays(first: Long, second: Long): List<DayWithHabits>

    @Query("SELECT * FROM Day WHERE date(date / 1000,'unixepoch') = date(:day / 1000,'unixepoch')")
    suspend fun getDay(day: Long): Day

}