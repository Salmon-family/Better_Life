package com.karrar.betterlife.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Habit(
    @PrimaryKey(autoGenerate = true) val habitID: Long = 0L,
    val name: String,
    val point: Int)
