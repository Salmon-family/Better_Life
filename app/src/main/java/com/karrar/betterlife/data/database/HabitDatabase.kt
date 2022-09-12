package com.karrar.betterlife.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.karrar.betterlife.data.Habit

@Database(entities = [Habit::class], version = 1)
abstract class HabitDatabase: RoomDatabase() {
    abstract fun betterLifeDao(): HabitDao

    companion object {
        private const val DATABASE_NAME = "BetterLiveDatabase"

        @Volatile
        private var instance: HabitDatabase? = null

        fun getInstance(context: Context): HabitDatabase {
            return instance ?: synchronized(this) { buildDatabase(context).also { instance = it } }
        }

        private fun buildDatabase(context: Context): HabitDatabase {
            return Room.databaseBuilder(context, HabitDatabase::class.java, DATABASE_NAME)
                .allowMainThreadQueries().build()
        }
    }
}