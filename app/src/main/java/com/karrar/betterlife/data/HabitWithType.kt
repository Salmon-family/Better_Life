package com.karrar.betterlife.data

import androidx.room.PrimaryKey

//Need to rename it...
data class HabitWithType(
    val id: Long,
    val name: String,
    val type: Boolean
)