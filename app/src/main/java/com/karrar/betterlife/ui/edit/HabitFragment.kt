package com.karrar.betterlife.ui.edit

import androidx.navigation.Navigation
import com.karrar.betterlife.R
import com.karrar.betterlife.databinding.FragmentEditHabitBinding
import com.karrar.betterlife.ui.HabitAdapter
import com.karrar.betterlife.ui.base.BaseFragment
import com.karrar.betterlife.util.EventObserve

class HabitFragment : BaseFragment<FragmentEditHabitBinding, HabitViewModel>(){
    override val layoutIdFragment = R.layout.fragment_edit_habit
    override val viewModelClass = HabitViewModel::class.java

    override fun setup() {
        naveEditHabitDialog()

        val adapter = HabitAdapter(mutableListOf(),viewModel)
        binding.recyclerHabitList.adapter = adapter
    }

    private fun naveEditHabitDialog() {

        viewModel.navigateTOEditHabitDialog.observe(this, EventObserve {id ->
          id?.let {
                Navigation.findNavController(binding.root)
                    .navigate(HabitFragmentDirections.actionEditFragmentToEditHabitDialog(id))
            }
        })
    }
}
