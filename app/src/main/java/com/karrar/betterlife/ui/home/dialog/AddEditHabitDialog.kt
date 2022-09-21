package com.karrar.betterlife.ui.home.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.karrar.betterlife.R
import com.karrar.betterlife.databinding.DialogAddEditHabitBinding
import com.karrar.betterlife.ui.base.BaseDialogFragment
import com.karrar.betterlife.util.DialogCancelClickEvent
import com.karrar.betterlife.util.EventObserve
import com.karrar.betterlife.util.setWidthPercent
import com.karrar.betterlife.util.showSnackMessage
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.cancelClickEvent.collectLatest { event ->
                dialogCancelEvent(event)
            }
        }
    }

    private fun dialogCancelEvent(event: DialogCancelClickEvent) {
        when (event) {
            is DialogCancelClickEvent.OnDialogCancelClick -> {
                dismiss()
            }
            is DialogCancelClickEvent.OnHabitUpdateClick -> {
                dismiss()
                activity?.showSnackMessage(R.id.constraint_habit_list_layout, getString(R.string.toast_update))
            }
            is DialogCancelClickEvent.OnHabitAddClick -> {
                dismiss()
                activity?.showSnackMessage(R.id.constraint_habit_list_layout, getString(R.string.toast_success))
            }
        }
    }

//    private fun dialogEvent(){
//        onCancelDialog()
//        onUpdateDialog()
//        onAddDialog()
//    }

//    private fun onCancelDialog() {
//        viewModel.isDialogCancel.observe(this, EventObserve {
//            if (it) {
//                dismiss()
//            }
//        })
//    }
//
//    private fun onUpdateDialog() {
//        viewModel.isHabitUpdate.observe(this, EventObserve {
//            if (it) {
//                dismiss()
//                activity?.showSnackMessage(R.id.constraint_habit_list_layout,getString(R.string.toast_update))
//            }
//        })
//    }
//
//    private fun onAddDialog() {
//        viewModel.isHabitAdd.observe(this, EventObserve {
//            if (it) {
//                dismiss()
//                activity?.showSnackMessage(R.id.constraint_habit_list_layout,getString(R.string.toast_success))
//            }
//        })
//    }
}