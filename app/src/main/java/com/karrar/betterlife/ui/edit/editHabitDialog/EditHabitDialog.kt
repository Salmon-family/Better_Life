package com.karrar.betterlife.ui.edit.editHabitDialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.karrar.betterlife.R
import com.karrar.betterlife.databinding.DialogEditHabitBinding
import com.karrar.betterlife.ui.base.BaseDialogFragment
import com.karrar.betterlife.util.EventObserve
import com.karrar.betterlife.util.setWidthPercent

class EditHabitDialog : BaseDialogFragment<DialogEditHabitBinding, EditHabitViewModel>() {
    override val viewModelClass: Class<EditHabitViewModel> = EditHabitViewModel::class.java
    override val layoutIdFragment: Int = R.layout.dialog_edit_habit

    override fun setup() {

        setWidthPercent(90)
        onEditHabitDone()
        closeDialog()
    }

    private fun onEditHabitDone() {
        viewModel.isEditHabit.observe(this, EventObserve {
            if (it) {
                dismiss()
                Toast.makeText(this.context, R.string.toast_success, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun closeDialog() {
        viewModel.isDialogClose.observe(this,EventObserve{
            if (it) {
                dismiss()
            }
        })
    }
}
//        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


