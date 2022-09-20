package com.karrar.betterlife.util

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView
import com.google.android.material.chip.Chip
import com.karrar.betterlife.R
import com.karrar.betterlife.data.database.DataCharts
import com.karrar.betterlife.data.database.entity.Task
import com.karrar.betterlife.ui.Charts
import com.karrar.betterlife.ui.toDo.temp.BaseAdapter

@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    (view.adapter as BaseAdapter<T>?)?.setItems(items ?: emptyList())
}

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

@BindingAdapter("app:colorBasedOnState")
fun changeColorBasedOnState(view: TextView, task: Task?) {
    task?.isChecked?.let {
        if (it) {
            view.setTextColor(view.getColor(R.color.red))
        } else {
            view.setTextColor(view.getColor(R.color.black))
        }
    }
}

@BindingAdapter("app:checkBasedOnState")
fun checkBasedOnState(view: CheckBox, task: Task?) {
    task?.isChecked?.let { view.isChecked = it }
}

fun View.getColor(color: Int) = ContextCompat.getColor(this.context, color)