package com.karrar.betterlife.util

import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.forEach
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.karrar.betterlife.R
import com.karrar.betterlife.data.database.DataCharts
import com.karrar.betterlife.ui.Charts

@BindingAdapter("app:doneToday")
fun setEnableButton(view: View, doneToday: Boolean) {
    view.isEnabled = !doneToday
}

@BindingAdapter("app:chipColor")
fun setChipsStyle(chip: Chip, good: Boolean) {
    when (good) {
        true -> {
            chip.setTextAppearanceResource(R.style.goodHabit)
            chip.setChipBackgroundColorResource(R.color.green_8)
            chip.chipStrokeColor =
                ContextCompat.getColorStateList(chip.context, R.color.selected_good_habit_chip)
        }
        else -> {
            chip.setTextAppearanceResource(R.style.badHabit)
            chip.setChipBackgroundColorResource(R.color.red_8)
            chip.chipStrokeColor =
                ContextCompat.getColorStateList(chip.context, R.color.selected_bad_habit_chip)
        }
    }
}

@BindingAdapter("app:shopCharts")
fun showCharts(view: AAChartView, dataCharts: DataCharts?) {
    dataCharts?.dataOfHabit?.let {
        val aaCharts = Charts(it.toTypedArray(), dataCharts.nameOfCategories.toTypedArray())
        view.aa_drawChartWithChartModel(aaCharts.drawCharts())
    }
}