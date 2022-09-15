package com.karrar.betterlife.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.database.entity.HabitResult

@Database(entities = [Habit::class, HabitResult::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class BetterLiveDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
    abstract fun habitResultDao(): HabitResultDao


    companion object {
        private const val DATABASE_NAME = "BetterLiveDatabase"

        @Volatile
        private var instance: BetterLiveDatabase? = null

        fun getInstance(context: Context): BetterLiveDatabase {
            return instance ?: synchronized(this) { buildDatabase(context, DateConverter()).also { instance = it } }
        }

        private fun buildDatabase(
            context: Context, dateConverter: DateConverter
        ): BetterLiveDatabase {
            return Room.databaseBuilder(context, BetterLiveDatabase::class.java, DATABASE_NAME)
                .addTypeConverter(dateConverter)
                .build()
        }
    }
}