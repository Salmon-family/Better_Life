package com.karrar.betterlife.util

import android.graphics.Paint
import android.os.CountDownTimer
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.karrar.betterlife.R
import com.karrar.betterlife.data.DataCharts
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.data.database.entity.Task
import com.karrar.betterlife.ui.Charts
import com.karrar.betterlife.ui.base.BaseAdapter

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
fun setCheckedChipId(view: ChipGroup?, names: List<String>?) {

}

@InverseBindingAdapter(attribute = "checkedChipButtonId", event = "checkedChipButtonId")
fun getChipId(view: ChipGroup?): List<String>? {
    val list = mutableListOf<String>()
    view?.children?.forEach {
        it as Chip
        if (it.isChecked) {
            list.add(it.text.toString())
        }
    }
    return list
}

@BindingAdapter("checkedChipButtonId")
fun setChipsListener(view: ChipGroup?, attChange: InverseBindingListener) {
    view?.setOnCheckedStateChangeListener { group, checkedId ->
        attChange.onChange()
    }
}

@BindingAdapter("app:colorBasedOnPointsValue")
fun setColorBasedOnPointsValue(view: TextView, points: Int) {
    if (points > 0) {
        view.setTextColor(view.getColorStateList(R.color.green))
    } else {
        view.setTextColor(view.getColorStateList(R.color.red))
    }
}

@BindingAdapter("app:showWhenEmptyHabitList")
fun showWhenEmptyHabitList(view: View, habits: List<Habit>?) {
    view.isVisible = habits.isNullOrEmpty()
    if (view is LottieAnimationView) view.playAnimation()
}

@BindingAdapter("app:showWhenNotEmptyHabitList")
fun showWhenNotEmptyHabitList(view: View, habits: List<Habit>?) {
    view.isVisible = !habits.isNullOrEmpty()
}

@BindingAdapter("app:colorBasedOnState")
fun changeColorBasedOnState(view: TextView, task: Task?) {
    task?.isChecked?.let {
        if (it) {
            view.setTextColor(view.getColor(R.color.black_30))
            view.isEnabled = false
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
            view.imageTintList = view.getColorStateList(R.color.black_30)
        } else {
            view.imageTintList = view.getColorStateList(R.color.gray)
        }
    }
}

@BindingAdapter("app:chipColorBasedOnState")
fun changeChipColorBasedOnState(view: CheckBox, task: Task?) {
    task?.isChecked?.let {
        if (it) {
            view.buttonTintList = view.getColorStateList(R.color.black_30)
            view.isEnabled = false
        } else {
            view.buttonTintList = view.getColorStateList(R.color.black)
        }
    }
}

@BindingAdapter("app:checkBasedOnState")
fun checkBasedOnState(view: CheckBox, task: Task?) {
    task?.isChecked?.let {
        view.isChecked = it
    }
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
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun afterTextChanged(editable: Editable) {
            if (null != view.layout && view.layout.lineCount > numOfLines) {
                view.text?.let { it.delete(it.length - 1, it.length) }
            }
        }
    })
}

@BindingAdapter("app:showWhenEmptyTaskList")
fun showWhenEmptyTaskList(view: LottieAnimationView, isEmptyList: Boolean?) {
    view.isVisible = isEmptyList != false
    view.playAnimation()
}

@BindingAdapter("app:showWhenNotEmptyTaskList")
fun showWhenNotEmptyTaskList(view: View, isEmptyList: Boolean?) {
    view.isVisible = isEmptyList == false
}

@BindingAdapter("app:doOnTextChanged")
fun doOnTextChanged(view: EditText, function: () -> Unit) {
    view.addTextChangedListener(object : TextWatcher {
        var timer: CountDownTimer? = null
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            view.setSelection(view.length())
        }

        override fun afterTextChanged(editable: Editable?) {
            timer?.cancel()
            timer = object : CountDownTimer(1000, 1500) {
                override fun onTick(millisUntilFinished: Long) {}
                override fun onFinish() {
                    function()
                }
            }.start()
        }
    })
}