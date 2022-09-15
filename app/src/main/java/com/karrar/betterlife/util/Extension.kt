package com.karrar.betterlife.util

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Rect
import android.os.Build
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

fun DialogFragment.setWidthPercent(percentage: Int) {
    val percent = percentage.toFloat() / 100
    val dm = Resources.getSystem().displayMetrics
    val rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
    val percentWidth = rect.width() * percent
    dialog?.window?.setLayout(percentWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
}

const val ONE_DAY_IN_MILLISECOND = 86400000

fun Date.minusDaysFromCurrentDayInMilliSeconds(numberOfDays: Int): Long {
    val numberOfDaysInMilliSecond = ONE_DAY_IN_MILLISECOND.times(numberOfDays)
    return this.time.minus(numberOfDaysInMilliSecond)
}

@SuppressLint("NewApi")
fun Long.formatDate(pattern: String): String {
    val localTime = Instant.ofEpochSecond(this)
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime()
    return localTime.format(DateTimeFormatter.ofPattern(pattern))

}