package com.karrar.betterlife.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.karrar.betterlife.R
import com.karrar.betterlife.data.Category
import com.karrar.betterlife.databinding.FragmentHomeBinding
import com.karrar.betterlife.ui.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val layoutIdFragment = R.layout.fragment_home
    override val viewModelClass = HomeViewModel::class.java

    private val categories = mutableListOf<Category>().apply {
        for (i in 0..100) {
            add(Category("category#$i", i))
        }
    }

    private lateinit var adapter: CategoryAdapter

    override fun setup() {
        //FOR TEST ONLY ...
//        adapter = CategoryAdapter(categories)
//        binding.recycler.layoutManager = GridLayoutManager(requireContext(), 15)
//        binding.recycler.adapter = adapter

        setupChipGroupDynamically()
    }


    private fun setupChipGroupDynamically() {
        val chipGroup = ChipGroup(requireContext())
        chipGroup.isSingleSelection = true
        categories.forEach {
            chipGroup.addView(createChip(it))
        }
        binding.chipGroup.addView(chipGroup)
    }

    private fun createChip(item: Category): Chip {
        val chip =
            LayoutInflater.from(requireActivity()).inflate(R.layout.item_category, null) as Chip
        chip.text = item.name
        if (item.id %2 ==0) {
            chip.chipStrokeColor = resources.getColorStateList(android.R.color.holo_red_dark)
        }else{
            chip.chipStrokeColor = resources.getColorStateList(android.R.color.holo_green_dark)
        }
        return chip
    }

}