package com.karrar.betterlife.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "RESULT_TABLE")
data class HabitResult(
    @PrimaryKey(autoGenerate = true) val id: Long,
    var id_habit: Long,
    val point: Int,
    val date: Int
)
