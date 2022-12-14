package com.karrar.betterlife.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.karrar.betterlife.BetterLifeApp
import com.karrar.betterlife.data.database.entity.DailyHabits
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.database.entity.Task


@Database(entities = [Habit::class, DailyHabits::class, Task::class], version = 2)
@TypeConverters(DateConverter::class)
abstract class BetterLiveDatabase : RoomDatabase() {

    abstract fun habitDao(): HabitDao
    abstract fun taskDao(): TaskDao

    companion object {
        private const val DATABASE_NAME = "BetterLiveDatabase"
        private const val DEFAULT_DATABASE_NAME = "BetterLiveDatabaseDefault.db"

        @Volatile
        private var instance: BetterLiveDatabase? = null

        fun getInstanceByApplicationContext(): BetterLiveDatabase {
            return instance ?: synchronized(this) {
                buildDatabase(
                    BetterLifeApp.applicationContext(),
                    DateConverter()
                ).also { instance = it }
            }
        }

        private fun buildDatabase(
            context: Context, dateConverter: DateConverter,
        ): BetterLiveDatabase {
            return Room.databaseBuilder(context, BetterLiveDatabase::class.java, DATABASE_NAME)
                .addTypeConverter(dateConverter)
                .createFromAsset(DEFAULT_DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}