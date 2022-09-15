package com.karrar.betterlife.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RESULT_TABLE")
data class HabitResult(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var id_habit: Long,
    val point: Int,
    val date: Long
)
