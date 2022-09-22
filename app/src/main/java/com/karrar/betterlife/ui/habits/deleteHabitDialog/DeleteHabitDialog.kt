package com.karrar.betterlife.ui.habits.deleteHabitDialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.karrar.betterlife.R
import com.karrar.betterlife.databinding.DialogDeleteHabitBinding
import com.karrar.betterlife.ui.base.BaseDialogFragment
import com.karrar.betterlife.util.EventObserve
import com.karrar.betterlife.util.setWidthPercent
import com.karrar.betterlife.util.showSnackMessage

class DeleteHabitDialog :
    BaseDialogFragment<DialogDeleteHabitBinding, DeleteHabitDialogViewModel>() {

    private val args: DeleteHabitDialogArgs by navArgs()

    override val viewModelClass: Class<DeleteHabitDialogViewModel> =
        DeleteHabitDialogViewModel::class.java
    override val layoutIdFragment: Int = R.layout.dialog_delete_habit


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
                activity?.showSnackMessage(
                    R.id.constraint_habit_list_layout,
                    getString(R.string.toast_delete)
                )
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



