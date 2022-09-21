package com.karrar.betterlife.ui.habits

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.karrar.betterlife.R
import com.karrar.betterlife.databinding.FragmentHabitBinding
import com.karrar.betterlife.ui.HabitAdapter
import com.karrar.betterlife.ui.base.BaseFragment
import com.karrar.betterlife.util.HabitFragmentClickEvent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HabitFragment : BaseFragment<FragmentHabitBinding, HabitViewModel>() {
    override val layoutIdFragment = R.layout.fragment_habit
    override val viewModelClass = HabitViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHabitAdapter()
        habitFragmentButtonClickEvent()
    }

    private fun setHabitAdapter() {
        val adapter = HabitAdapter(mutableListOf(), viewModel)
        binding.recyclerHabitList.adapter = adapter
    }

    private fun habitFragmentButtonClickEvent() {
        val navigate = Navigation.findNavController(binding.root)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.onClickEvent.collectLatest { event ->
                when (event) {
                    is HabitFragmentClickEvent.OnAddHabit -> navigate.navigate(
                        HabitFragmentDirections.actionEditFragmentToAddEditHabitDialog(-1)
                    )

                    is HabitFragmentClickEvent.OnEditHabit -> navigate.navigate(
                        HabitFragmentDirections.actionEditFragmentToAddEditHabitDialog(event.long)
                    )

                    is HabitFragmentClickEvent.OnDeleteHabit -> navigate.navigate(
                        HabitFragmentDirections.actionEditFragmentToDeleteHabitDialog(event.long)
                    )
                }
            }
        }
    }
}
