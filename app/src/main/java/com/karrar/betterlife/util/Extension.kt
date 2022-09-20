package com.karrar.betterlife.util

import android.content.Context
import android.content.res.Resources
import android.graphics.Rect
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.HabitWithType
import com.karrar.betterlife.util.Constants.BAD
import com.karrar.betterlife.util.Constants.GOOD
import java.text.SimpleDateFormat
import java.util.*

fun DialogFragment.setWidthPercent(percentage: Int) {
    val percent = percentage.toFloat() / 100
    val dm = Resources.getSystem().displayMetrics
    val rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
    val percentWidth = rect.width() * percent
    dialog?.window?.setLayout(percentWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
}


fun Habit.asHabitWithType(): HabitWithType {
    return HabitWithType(
            id = id,
            name = name,
            type = if (point>0) GOOD else BAD
        )
}

fun Long.toStringDate(): String {
    val date = Date(this)
    val format = SimpleDateFormat("yyyy-MM-dd")
    return format.format(date)
}

fun Long.toDate(): Long {
    val format = SimpleDateFormat("yyyy-MM-dd")
    return format.parse(format.format(this)).time
}