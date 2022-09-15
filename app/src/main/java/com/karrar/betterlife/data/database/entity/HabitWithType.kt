package com.karrar.betterlife.data.database.entity

import androidx.room.PrimaryKey

//Need to rename it...
data class HabitWithType(
    val id: Long,
    val name: String,
    val type: Boolean
)