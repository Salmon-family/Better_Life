package com.karrar.betterlife.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "TASK_TABLE")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val note: String,
    val date: Date,
    var isChecked: Boolean
)