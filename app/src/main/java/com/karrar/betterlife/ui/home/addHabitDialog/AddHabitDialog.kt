package com.karrar.betterlife.ui.home.addHabitDialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.karrar.betterlife.R
import com.karrar.betterlife.databinding.DialogAddHabitBinding
import com.karrar.betterlife.ui.base.BaseDialogFragment


class AddHabitDialog : BaseDialogFragment<DialogAddHabitBinding, AddHabitViewModel>(){
    override val viewModelClass: Class<AddHabitViewModel> = AddHabitViewModel::class.java
    override val layoutIdFragment: Int = R.layout.dialog_add_habit

    override fun setup() {
        addNewHabitAndBackToHome()
    }

    private fun addNewHabitAndBackToHome(){
        viewModel.isAddHabit.observe(viewLifecycleOwner){
            findNavController().popBackStack()
            Toast.makeText(this.context, "Add habit is successfully", Toast.LENGTH_SHORT).show()
        }
    }

}