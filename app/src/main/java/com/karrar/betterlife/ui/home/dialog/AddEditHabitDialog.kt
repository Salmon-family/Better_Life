package com.karrar.betterlife.ui.home.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.karrar.betterlife.R
import com.karrar.betterlife.databinding.DialogAddEditHabitBinding
import com.karrar.betterlife.ui.base.BaseDialogFragment
import com.karrar.betterlife.util.DialogCancelClickEvent
import com.karrar.betterlife.util.EventObserve
import com.karrar.betterlife.util.setWidthPercent
import com.karrar.betterlife.util.showSnackMessage

class AddEditHabitDialog : BaseDialogFragment<DialogAddEditHabitBinding, AddEditHabitViewModel>() {

    private val args: AddEditHabitDialogArgs by navArgs()

    override val viewModelClass: Class<AddEditHabitViewModel> = AddEditHabitViewModel::class.java
    override val layoutIdFragment: Int = R.layout.dialog_add_edit_habit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWidthPercent(90)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        viewModel.initializeDialogDependOnValueFromNavigationArgs(args.addEditHabit)
        dialogEventCancelClick()
    }

    private fun dialogEventCancelClick() {
        viewModel.cancelClickEvent.observe(this, EventObserve {
            dialogCancelEvent(it)
        })
    }

    private fun dialogCancelEvent(event: DialogCancelClickEvent) {
        when (event) {
            is DialogCancelClickEvent.OnDialogCancelClick -> {
                dismiss()
            }
            is DialogCancelClickEvent.OnHabitUpdateClick -> {
                dismiss()
                activity?.showSnackMessage(
                    R.id.constraint_habit_list_layout,
                    getString(R.string.toast_update)
                )
            }
            is DialogCancelClickEvent.OnHabitAddClick -> {
                dismiss()
                activity?.showSnackMessage(
                    R.id.constraint_habit_list_layout,
                    getString(R.string.toast_success)
                )
            }
        }
    }

}