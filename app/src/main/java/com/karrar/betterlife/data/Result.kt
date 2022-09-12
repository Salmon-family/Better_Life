package com.karrar.betterlife.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RESULT_TABLE")
data class Result(
    @PrimaryKey(autoGenerate = true) val id: Long,
    var id_habit: Long,
    val point: Int,
    val date: Int
)
