package com.karrar.betterlife.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface BetterLiveDao<T> {

    @Insert
    suspend fun insert(BetterLive: T)

    @Update
    suspend fun update(BetterLive: T)

    @Delete
    suspend fun delete(BetterLive: T)

}