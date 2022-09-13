package com.karrar.betterlife.data.database.repositories

import com.karrar.betterlife.data.database.BetterLiveDatabase
import com.karrar.betterlife.data.database.HabitDao
import com.karrar.betterlife.data.database.entity.Habit

class HabitRepository {

    val dao = BetterLiveDatabase.getInstance(activity?.applicationContext!!).habitDao()

   suspend fun deleteHabit(habit:Habit){
       return dao.delete(habit)
    }

    suspend fun updateHabit(habit:Habit){
        return dao.update(habit)
    }

    fun getAllHabit() = dao.getAllHabit()

}