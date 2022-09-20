package com.karrar.betterlife.ui.edit.deleteHabitDialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.karrar.betterlife.R
import com.karrar.betterlife.databinding.DialogDeleteHabitBinding
import com.karrar.betterlife.databinding.DialogEditHabitBinding
import com.karrar.betterlife.ui.base.BaseDialogFragment
import com.karrar.betterlife.util.EventObserve
import com.karrar.betterlife.util.setWidthPercent

class DeleteHabitDialog :
    BaseDialogFragment<DialogDeleteHabitBinding, DeleteHabitDialogViewModel>() {

    private val args: DeleteHabitDialogArgs by navArgs()

    override val viewModelClass: Class<DeleteHabitDialogViewModel> =
        DeleteHabitDialogViewModel::class.java
    override val layoutIdFragment: Int = R.layout.dialog_delete_habit


    override fun setup() {

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setWidthPercent(90)
        onDeleteHabitDone()
        cancelDialog()
        viewModel.getHabitById(args.deleteHabitId)
    }

    private fun onDeleteHabitDone() {
        viewModel.isDeleteHabit.observe(this, EventObserve {
            if (it) {
                dismiss()
                Toast.makeText(this.context, R.string.delete, Toast.LENGTH_SHORT).show()
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



