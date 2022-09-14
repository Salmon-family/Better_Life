package com.karrar.betterlife.util

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.ChipGroup
import com.karrar.betterlife.ui.base.BaseAdapter

@BindingAdapter(value = ["checkedChipButtonId"])
fun setCheckedChipId(view: ChipGroup?, id: Int) {
    if (view?.checkedChipId != id) {
        view?.check(id)
    }
}

@InverseBindingAdapter(attribute = "checkedChipButtonId", event = "checkedChipButtonId")
fun getChipId(view: ChipGroup?): Int? {
    return view?.checkedChipId
}

@BindingAdapter("checkedChipButtonId")
fun setChipsListener(view: ChipGroup?, attChange: InverseBindingListener) {
    view?.setOnCheckedStateChangeListener { group, checkedId ->
        attChange.onChange()
    }
}

@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items:List<T>?) {
    if (items != null){
        (view.adapter as BaseAdapter<T>?)?.setItems(items)
    }else
        (view.adapter as BaseAdapter<T>?)?.setItems((emptyList()))
}
