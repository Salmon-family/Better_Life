package com.karrar.betterlife.util

import android.graphics.Paint
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
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
import com.karrar.betterlife.ui.tasks.temp.BaseAdapter

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
            view.setTextColor(view.getColor(R.color.black_30))
            view.paintFlags = view.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            view.setTextColor(view.getColor(R.color.black))
            view.paintFlags = view.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}

@BindingAdapter("app:colorBasedOnState")
fun changeImageColorBasedOnState(view: ImageView, task: Task?) {
    task?.isChecked?.let {
        if (it) {
            view.setImageResource(R.drawable.ic_x_selected)
        } else {
            view.setImageResource(R.drawable.ic_x)
        }
    }
}

@BindingAdapter("app:chipColorBasedOnState")
fun changeChipColorBasedOnState(view: CheckBox, task: Task?) {
    task?.isChecked?.let {
        if (it) {
            view.buttonTintList = view.getColorStateList(R.color.black_30)
        } else {
            view.buttonTintList = view.getColorStateList(R.color.black)
        }
    }
}

@BindingAdapter("app:checkBasedOnState")
fun checkBasedOnState(view: CheckBox, task: Task?) {
    task?.isChecked?.let { view.isChecked = it }
}

@BindingAdapter("app:maxNumberOfLines")
fun setMaxNumberOfLines(view: EditText, numOfLines: Int) {
    if (numOfLines > 1) {
        view.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_MULTI_LINE
    } else {
        view.inputType = InputType.TYPE_CLASS_TEXT
    }

    view.isSingleLine = false

    view.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {}
        override fun onTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {}

        override fun afterTextChanged(editable: Editable) {
            if (null != view.layout && view.layout.lineCount > numOfLines) {
                view.text?.let { it.delete(it.length - 1, it.length) }
            }
        }
    })
}