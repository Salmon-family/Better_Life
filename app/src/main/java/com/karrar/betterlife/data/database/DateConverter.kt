package com.karrar.betterlife.data.database

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun longToDate(dateLong: Long): Date {
        return Date(dateLong)
    }

    @TypeConverter
    fun dateToLong(date: Date): Long {
        return date.time
    }
}