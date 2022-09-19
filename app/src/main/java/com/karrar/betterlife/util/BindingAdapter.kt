package com.karrar.betterlife.util

import android.view.View
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

@BindingAdapter("app:setHabits")
fun setHabitsChips(chipGroup: ChipGroup, habits: List<Habit>?) {
    habits?.let {
        it.forEach { habit ->
            chipGroup.addView(chipGroup.createChip(habit))
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

@BindingAdapter(value = ["checkedChipButtonId"])
fun setCheckedChipId(view: ChipGroup?, ids: List<Int>?) {
    ids?.let {
        if (view?.checkedChipId != it.first()) {
            view?.check(it.first())
        }
    }
}

@InverseBindingAdapter(attribute = "checkedChipButtonId", event = "checkedChipButtonId")
fun getChipId(view: ChipGroup?): List<Int>? {
    return view?.checkedChipIds
}

@BindingAdapter("checkedChipButtonId")
fun setChipsListener(view: ChipGroup?, attChange: InverseBindingListener) {
    view?.setOnCheckedStateChangeListener { group, checkedId ->
        attChange.onChange()
    }
}