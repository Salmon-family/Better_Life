package com.karrar.betterlife.ui.edit.editHabitDialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.karrar.betterlife.R
import com.karrar.betterlife.databinding.DialogEditHabitBinding
import com.karrar.betterlife.ui.base.BaseDialogFragment
import com.karrar.betterlife.util.EventObserve
import com.karrar.betterlife.util.setWidthPercent

class EditHabitDialog : BaseDialogFragment<DialogEditHabitBinding, EditHabitDialogViewModel>() {

    val args: EditHabitDialogArgs by navArgs()

    override val viewModelClass: Class<EditHabitDialogViewModel> =
        EditHabitDialogViewModel::class.java
    override val layoutIdFragment: Int = R.layout.dialog_edit_habit


    override fun setup() {

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setWidthPercent(90)
        onEditHabitDone()
        cancelDialog()
        viewModel.getHabitById(args.sendHabitId)
    }

    private fun onEditHabitDone() {
        viewModel.isEditHabit.observe(this, EventObserve {
            if (it) {
                dismiss()
                Toast.makeText(this.context, R.string.toast_update, Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun cancelDialog() {
        viewModel.isDialogClose.observe(this, EventObserve {
            if (it) {
                dismiss()
            }
        })
    }
}



