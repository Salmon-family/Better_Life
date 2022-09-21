package com.karrar.betterlife.ui.edit

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.karrar.betterlife.R
import com.karrar.betterlife.databinding.FragmentEditHabitBinding
import com.karrar.betterlife.ui.HabitAdapter
import com.karrar.betterlife.ui.base.BaseFragment
import com.karrar.betterlife.util.HabitFragmentClickEvent

class HabitFragment : BaseFragment<FragmentEditHabitBinding, HabitViewModel>() {
    override val layoutIdFragment = R.layout.fragment_edit_habit
    override val viewModelClass = HabitViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHabitAdapter()
        clickEvent()
    }

    private fun setHabitAdapter() {
        val adapter = HabitAdapter(mutableListOf(), viewModel)
        binding.recyclerHabitList.adapter = adapter

    }

    private fun clickEvent(){
        val navigate = Navigation.findNavController(binding.root)
        viewModel.onClickEvent.observe(viewLifecycleOwner){ event->
            when(event){
                is HabitFragmentClickEvent.OnAddHabit-> navigate.navigate(
                    HabitFragmentDirections.actionEditFragmentToAddEditHabitDialog(""))

                is HabitFragmentClickEvent.OnEditHabit-> navigate.navigate(
                    HabitFragmentDirections.actionEditFragmentToAddEditHabitDialog("${event.long}"))

                is HabitFragmentClickEvent.OnDeleteHabit-> navigate.navigate(
                    HabitFragmentDirections.actionEditFragmentToDeleteHabitDialog(event.long))
            }
        }
    }
}
