package com.karrar.betterlife.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "HABIT_TABLE")
data class Habit(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val point: Int,
)
