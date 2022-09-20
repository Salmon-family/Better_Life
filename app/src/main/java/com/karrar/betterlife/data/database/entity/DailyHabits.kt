package com.karrar.betterlife.data.database.entity

import androidx.room.Entity

@Entity(primaryKeys = ["dayID","habitID"])
data class DailyHabits(
    val dayID: Long,
    val habitID: Long
)