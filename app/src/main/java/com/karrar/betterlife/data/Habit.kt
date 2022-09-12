package com.karrar.betterlife.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BETTER_LIFE_TABLE")
data class Habit(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val type: Boolean,
    val points: Int,
)
