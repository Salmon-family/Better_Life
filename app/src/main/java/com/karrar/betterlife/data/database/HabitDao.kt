package com.karrar.betterlife.data.database

import androidx.room.*
import com.karrar.betterlife.data.database.entity.DailyHabits
import com.karrar.betterlife.data.database.entity.Habit
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {

    @Insert
    suspend fun insert(habit: Habit)

    @Insert
    suspend fun insertHabits(habits: List<Habit>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(todayHabits: DailyHabits)


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(todayHabits: List<DailyHabits>)

    @Update
    suspend fun update(habit: Habit)

    @Delete
    suspend fun delete(habit: Habit)

    @Query("SELECT * FROM habit")
    fun getAllHabit(): Flow<List<Habit>>

    @Query(" SELECT * FROM dailyhabits WHERE date(dayID / 1000,'unixepoch')  ==  date(:day / 1000,'unixepoch')")
    suspend fun isAnyHabitsInThisDay(day: Long): List<DailyHabits>

    @Query("SELECT SUM(HABIT.point) FROM HABIT, DailyHabits WHERE HABIT.habitID == dailyhabits.habitID AND(dailyhabits.dayID == :day)")
    suspend fun getAllHabitPerDay(day: Long): Int

    @Query("SELECT SUM(HABIT.point) FROM HABIT, DailyHabits WHERE HABIT.habitID == dailyhabits.habitID GROUP BY dailyhabits.dayID")
    suspend fun getAllDaysPoints(): List<Int>

    @Query("SELECT SUM(HABIT.point) AS pointsResult, DailyHabits.dayID AS dateResult  FROM HABIT, DailyHabits WHERE HABIT.habitID == dailyhabits.habitID  AND DailyHabits.dayID BETWEEN :startDay AND :endDay GROUP BY dailyhabits.dayID")
    suspend fun getPointsInRange(startDay: Long, endDay: Long): List<PointsResult>

    @Query("SELECT SUM(HABIT.point) AS pointsResult , DailyHabits.dayID AS dateResult FROM HABIT, DailyHabits WHERE HABIT.habitID == dailyhabits.habitID GROUP BY strftime('%Y-%m', dayID / 1000, 'unixepoch')")
    suspend fun getPointsMonthlyWithDate(): List<PointsResult>

    /**
     * given max year return all months
     * */
    @Query("SELECT SUM(HABIT.point) AS pointsResult, DailyHabits.dayID AS dateResult FROM HABIT, DailyHabits WHERE HABIT.habitID == dailyhabits.habitID AND DailyHabits.dayID <= :maxYear GROUP BY strftime('%Y-%m', dayID / 1000, 'unixepoch')")
    suspend fun getPointDuringYearWithDate(maxYear: Long): List<PointsResult>

}