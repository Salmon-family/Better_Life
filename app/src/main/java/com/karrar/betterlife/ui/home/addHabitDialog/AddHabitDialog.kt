package com.karrar.betterlife.ui.home.addHabitDialog

import com.karrar.betterlife.R
import com.karrar.betterlife.databinding.DialogAddHabitBinding
import com.karrar.betterlife.ui.base.BaseDialogFragment


class AddHabitDialog : BaseDialogFragment<DialogAddHabitBinding, AddHabitViewModel>(){
    override val viewModelClass: Class<AddHabitViewModel> = AddHabitViewModel::class.java
    override val layoutIdFragment: Int = R.layout.dialog_add_habit

    override fun setup() {

    }

}