package com.karrar.betterlife.data.database.entity

import androidx.room.Embedded
import androidx.room.Ignore
import androidx.room.Junction
import androidx.room.Relation

data class DayWithHabits(
    @Embedded var day: Day?,

    @Relation(
        parentColumn = "dayID",
        entityColumn = "habitID",
        associateBy = Junction(DailyHabits::class)
    )
    var habits: List<Habit>?
){
    @Ignore
    fun sumPoints(): Int {
        return habits?.let {
            it.sumOf { it.point }
        }?:0
    }
}