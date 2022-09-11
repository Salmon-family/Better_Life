package com.karrar.betterlife.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.karrar.betterlife.data.BetterLife

@Database(entities = [BetterLife::class], version = 1)
abstract class BetterLifeDatabase: RoomDatabase() {
    abstract fun betterLifeDao(): BetterLifeDao

    companion object{
        private const val DATABASE_NAME = "BetterLiveDatabase"
        private var instance: BetterLifeDatabase? = null

        fun getInstance(context: Context):BetterLifeDatabase{
            return instance ?: synchronized(this){buildDatabase(context).also { instance = it } }
        }

        private fun buildDatabase(context: Context):BetterLifeDatabase{
            return Room.databaseBuilder(context,BetterLifeDatabase::class.java, DATABASE_NAME).build()
        }
    }
}