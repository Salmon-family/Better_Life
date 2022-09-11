package com.karrar.betterlife.ui.utils

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.google.android.material.chip.ChipGroup


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
    view?.setOnCheckedStateChangeListener { _, _ ->
        attChange.onChange()
    }
}

//@BindingAdapter(value = ["android:onCheckedChanged", "android:checkedButtonAttributes"], requireAll = false)
//fun setChipsListeners(
//    view: ChipGroup?,
//    listener: ChipGroup.OnCheckedStateChangeListener?,
//    attributes: InverseBindingListener?
//) {
//
//    if (attributes == null) {
//        view?.setOnCheckedStateChangeListener(listener)
//    } else {
//        view?.setOnCheckedStateChangeListener { group, checkedId ->
//            listener?.onCheckedChanged(group, checkedId)
//            attributes.onChange()
//        }
//    }
//}
//

//@InverseBindingMethods(InverseBindingMethod(type = ChipGroup::class, attribute = "android:checkedButton", method = "getCheckedChipId"))
//class ChipGroupBindingAdapter {
//    companion object {
//        @JvmStatic
//        @BindingAdapter("android:checkedButton")
//        fun setCheckedChip(view: ChipGroup?, id: Int) {
//            if (id != view?.checkedChipId) {
//                view?.check(id)
//            }
//        }
//
//        @JvmStatic
//        @BindingAdapter(value = ["android:onCheckedChanged", "android:checkedButtonAttrChanged"], requireAll = false)
//        fun setChipsListeners(view: ChipGroup?, listener: ChipGroup.OnCheckedChangeListener?,
//                              attrChange: InverseBindingListener?) {
//            if (attrChange == null) {
//                view?.setOnCheckedChangeListener(listener)
//            } else {
//                view?.setOnCheckedChangeListener { group, checkedId ->
//                    listener?.onCheckedChanged(group, checkedId)
//                    attrChange.onChange()
//                }
//            }
//        }
//    }
//}