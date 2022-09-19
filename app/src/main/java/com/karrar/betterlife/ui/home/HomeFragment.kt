package com.karrar.betterlife.ui.home

import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.karrar.betterlife.R
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.databinding.FragmentHomeBinding
import com.karrar.betterlife.databinding.ItemCategoryBinding
import com.karrar.betterlife.ui.base.BaseFragment
import com.karrar.betterlife.util.EventObserve

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val layoutIdFragment = R.layout.fragment_home
    override val viewModelClass = HomeViewModel::class.java

    override fun setup() {
        setupChipGroupDynamically()
        navigateToAddHabitDialog()
        navigateToHabit()
    }

    private fun navigateToAddHabitDialog() {
        viewModel.navigateAddHabit.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.addHabitDialog)
        }
    }

    private fun navigateToHabit() {
             viewModel.navigateHabit.observe(this, EventObserve {
            if (it) {
                Navigation.findNavController(binding.root)
                    .navigate(HomeFragmentDirections.actionHomeFragmentToEditFragment())
            }
        })
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
        val chipBinding: ItemCategoryBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.item_category,
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