package com.karrar.betterlife.ui.habits.habitDialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.karrar.betterlife.R
import com.karrar.betterlife.databinding.DialogHabitBinding
import com.karrar.betterlife.ui.base.BaseDialogFragment
import com.karrar.betterlife.util.DialogClickEvent
import com.karrar.betterlife.util.EventObserve
import com.karrar.betterlife.util.setWidthPercent
import com.karrar.betterlife.util.showSnackMessage

class HabitDialog : BaseDialogFragment<DialogHabitBinding, HabitViewModel>() {

    private val args: HabitDialogArgs by navArgs()

    override val viewModelClass: Class<HabitViewModel> = HabitViewModel::class.java
    override val layoutIdFragment: Int = R.layout.dialog_habit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWidthPercent(90)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        viewModel.initializeDialogDependOnValueFromNavigationArgs(args.addEditHabit)
        observeClickEvent()
    }

    private fun observeClickEvent() {
        viewModel.dialogClickEvent.observe(this, EventObserve {
            dialogCancelEvent(it)
        })
    }

    private fun dialogCancelEvent(event: DialogClickEvent) {
        when (event) {
            is DialogClickEvent.OnDialogClick -> {
                dismiss()
            }
            is DialogClickEvent.OnHabitUpdateClick -> {
                dismiss()
                activity?.showSnackMessage(
                    R.id.constraint_habit_list_layout,
                    getString(R.string.toast_update)
                )
            }
            is DialogClickEvent.OnHabitAddClick -> {
                dismiss()
                activity?.showSnackMessage(
                    R.id.constraint_habit_list_layout,
                    getString(R.string.toast_success)
                )
            }
        }
    }

}