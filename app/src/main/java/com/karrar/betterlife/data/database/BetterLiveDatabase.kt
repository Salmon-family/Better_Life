package com.karrar.betterlife.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.karrar.betterlife.data.DayResult
import com.karrar.betterlife.data.database.entity.Habit

@Database(entities = [Habit::class, DayResult::class], version = 1)
abstract class BetterLiveDatabase: RoomDatabase() {
    abstract fun <T> betterLiveDao(): BetterLiveDao<T>


    companion object {
        private const val DATABASE_NAME = "BetterLiveDatabase"

        @Volatile
        private var instance: BetterLiveDatabase? = null

        fun getInstance(context: Context): BetterLiveDatabase {
            return instance ?: synchronized(this) { buildDatabase(context).also { instance = it } }
        }

        private fun buildDatabase(context: Context): BetterLiveDatabase {
            return Room.databaseBuilder(context, BetterLiveDatabase::class.java, DATABASE_NAME).build()
        }
    }
}