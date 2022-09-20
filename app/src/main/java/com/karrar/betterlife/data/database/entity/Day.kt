package com.karrar.betterlife.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Day (
    @PrimaryKey(autoGenerate = true) val dayID: Long = 0,
    val date: Date
)