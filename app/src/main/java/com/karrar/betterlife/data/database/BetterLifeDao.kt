package com.karrar.betterlife.data.database

import androidx.room.*
import com.karrar.betterlife.data.BetterLife

@Dao
interface BetterLifeDao {

    @Insert
    fun insert(betterLive: BetterLife)

    @Update
    fun update(betterLive: BetterLife)

    @Delete
    fun delete(betterLive: BetterLife)

    @Query("SELECT * FROM BETTER_LIFE_TABLE")
    fun getAllBetterLife(): List<BetterLife>

}