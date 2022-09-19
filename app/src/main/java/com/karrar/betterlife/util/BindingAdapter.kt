package com.karrar.betterlife.util

import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.forEach
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.karrar.betterlife.R
import com.karrar.betterlife.data.database.DataCharts
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.ui.Charts
import com.karrar.betterlife.ui.home.HomeViewModel

@BindingAdapter("app:chipColor")
fun setChipsStyle(chip: Chip, good: Boolean) {
    when (good) {
        true -> {
            chip.setTextAppearanceResource(R.style.goodHabit)
            chip.setChipBackgroundColorResource(R.color.green_8)
            chip.chipStrokeColor = chip.getColorStateList(R.color.selected_good_habit_chip)
        }
        else -> {
            chip.setTextAppearanceResource(R.style.badHabit)
            chip.setChipBackgroundColorResource(R.color.red_8)
            chip.chipStrokeColor = chip.getColorStateList(R.color.selected_bad_habit_chip)
        }
    }
}

//need to remove home vm
@BindingAdapter(value = ["app:setHabits", "app:setViewModel"])
fun setHabitsChips(chipGroup: ChipGroup, habits: List<Habit>?, viewModel: HomeViewModel) {
    habits?.let {
        it.forEach { habit ->
            chipGroup.addView(chipGroup.createChip(habit, viewModel))
        }
    }
}

@BindingAdapter("app:isDoneToday")
fun isDoneTodayHabits(view: View, doneToday: Boolean) {
    view.isVisible = !doneToday
}

@BindingAdapter("app:showDoneToday")
fun showDoneToday(view: View, doneToday: Boolean) {
    view.isVisible = doneToday
}

@BindingAdapter("app:shopCharts")
fun showCharts(view: AAChartView, dataCharts: DataCharts?) {
    dataCharts?.dataOfHabit?.let {
        val aaCharts = Charts(it.toTypedArray(), dataCharts.nameOfCategories.toTypedArray())
        view.aa_drawChartWithChartModel(aaCharts.drawCharts())
    }
}