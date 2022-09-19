package com.karrar.betterlife.util

import android.content.Context
import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.DialogFragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.make
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.HabitWithType
import com.karrar.betterlife.util.Constants.BAD
import com.karrar.betterlife.util.Constants.GOOD

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
        type = if (point > 0) GOOD else BAD
    )
}

fun Toast.showMessage(message: String, context: Context) {
    makeText(context, message, Toast.LENGTH_SHORT).show()
}
