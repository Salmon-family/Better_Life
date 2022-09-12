package com.karrar.betterlife.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface BetterLiveDao<T> {

    @Insert
    fun insert(BetterLive: T)

    @Update
    fun update(BetterLive: T)

    @Delete
    fun delete(BetterLive: T)

}