package com.karrar.betterlife.ui.tasks.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import com.karrar.betterlife.R
import com.karrar.betterlife.databinding.DialogTaskAdditionBinding
import com.karrar.betterlife.ui.base.BaseDialogFragment
import com.karrar.betterlife.ui.tasks.TasksViewModel
import com.karrar.betterlife.util.EventObserve
import com.karrar.betterlife.util.setWidthPercent

class TaskAdditionDialog : BaseDialogFragment<DialogTaskAdditionBinding, TasksViewModel>() {
    override val viewModelClass: Class<TasksViewModel> = TasksViewModel::class.java
    override val layoutIdFragment: Int = R.layout.dialog_task_addition

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWidthPercent(90)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        observeEvents()
    }

    private fun observeEvents() {
        viewModel.onClickCancelEvent.observe(this, EventObserve {
            if (it) dismiss()
        })

        viewModel.onClickAddEvent.observe(this, EventObserve {
            if (it) dismiss()
        })
    }

}