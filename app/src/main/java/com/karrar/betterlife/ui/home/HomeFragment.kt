package com.karrar.betterlife.ui.home

import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.karrar.betterlife.R
import com.karrar.betterlife.data.DailyHabit
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.databinding.FragmentHomeBinding
import com.karrar.betterlife.databinding.ItemHabitBinding
import com.karrar.betterlife.ui.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val layoutIdFragment = R.layout.fragment_home
    override val viewModelClass = HomeViewModel::class.java

    override fun setup() {
        val list = mutableListOf<DailyHabit>()
        for (i in 0..15){
            list.add(DailyHabit("Fri",100))
        }
        val adapter = HabitDailyAdapter(list)
        binding.recyclerDay.adapter = adapter

        setupChipGroupDynamically()
        navigateToAddHabitDialog()
    }

    private fun navigateToAddHabitDialog() {
        viewModel.navigateAddHabit.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.addHabitDialog)
        }
    }

    private fun setupChipGroupDynamically() {
        viewModel.habits.observe(this) {
            binding.habitsChipGroup.removeAllViews()
            it?.let {
                it.forEach { habit ->
                    binding.habitsChipGroup.addView(createChip(habit))
                }
            }
        }
    }

    private fun createChip(item: Habit): Chip {
        val chipBinding: ItemHabitBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.item_habit,
            binding.habitsChipGroup,
            false
        )
        chipBinding.viewModel = viewModel
        chipBinding.item = item
        val chip = chipBinding.root as Chip
        if (item.point % 2 == 0) {
            chip.setTextAppearanceResource(R.style.badHabit)
            chip.setChipBackgroundColorResource(R.color.red_8)
            chip.chipStrokeColor =
                ContextCompat.getColorStateList(requireContext(), R.color.selected_bad_habit_chip)
        } else {
            chip.setTextAppearanceResource(R.style.goodHabit)
            chip.setChipBackgroundColorResource(R.color.green_8)
            chip.chipStrokeColor =
                ContextCompat.getColorStateList(requireContext(), R.color.selected_good_habit_chip)
        }
        return chip
    }

}