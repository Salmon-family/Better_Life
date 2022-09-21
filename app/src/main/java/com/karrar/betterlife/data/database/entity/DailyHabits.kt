package com.karrar.betterlife.data.database.entity

import androidx.room.Entity
import java.util.Date

@Entity(primaryKeys = ["dayID","habitID"])
data class DailyHabits(
    val dayID: Date,
    val habitID: Long
)