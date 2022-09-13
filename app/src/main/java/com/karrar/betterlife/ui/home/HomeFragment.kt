package com.karrar.betterlife.ui.home

import android.view.LayoutInflater
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.karrar.betterlife.R
import com.karrar.betterlife.data.database.entity.Habit
import com.karrar.betterlife.databinding.FragmentHomeBinding
import com.karrar.betterlife.ui.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val layoutIdFragment = R.layout.fragment_home
    override val viewModelClass = HomeViewModel::class.java

    override fun setup() {
        setupChipGroupDynamically()
        viewModel.checkedBtnObs.observe(this) {
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupChipGroupDynamically() {
        binding.chipGroup.isSingleSelection = true
        viewModel.habits?.let {
            it.observe(this) {
                binding.chipGroup.removeAllViews()
                it?.let {
                    it.forEach { habit ->
                        binding.chipGroup.addView(createChip(habit))
                    }
                }
            }
        }
    }

    private fun createChip(item: Habit): Chip {
        val chip =
            LayoutInflater.from(requireActivity()).inflate(R.layout.item_category, null) as Chip
        chip.text = item.name
        if (item.point % 2 == 0) {
            chip.chipStrokeColor = resources.getColorStateList(android.R.color.holo_red_dark)
        } else {
            chip.chipStrokeColor = resources.getColorStateList(android.R.color.holo_green_dark)
        }
        return chip
    }

}