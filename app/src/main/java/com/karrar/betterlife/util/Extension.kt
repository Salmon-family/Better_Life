package com.karrar.betterlife.util

import android.app.Activity
import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.google.android.material.chip.ChipGroup
import com.karrar.betterlife.R
import com.google.android.material.snackbar.Snackbar
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.HabitWithType
import com.karrar.betterlife.databinding.ItemHabitBinding
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
    dialog?.setCanceledOnTouchOutside(false)
}

fun Habit.asHabitWithType(): HabitWithType {
    return HabitWithType(
        id = habitID,
        name = name,
        type = if (point > 0) GOOD else BAD
    )
}

fun View.getColor(color: Int) = ContextCompat.getColor(this.context, color)

fun View.getColorStateList(color: Int) = ContextCompat.getColorStateList(this.context, color)


fun ChipGroup.createChip(item: Habit): View {
    val chipBinding: ItemHabitBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.item_habit,
        this,
        false
    )
    chipBinding.item = item.asHabitWithType()
    return chipBinding.root
}
fun Activity.showSnackMessage(idLayout: Int, message: String) {
    Snackbar.make(this.findViewById(idLayout), message, Snackbar.LENGTH_LONG).show()
}

fun Long.toStringDate(format: String): String {
    val date = Date(this)
    val format = SimpleDateFormat(format)
    return format.format(date)
}
