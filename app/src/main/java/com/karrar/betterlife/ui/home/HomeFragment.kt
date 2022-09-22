package com.karrar.betterlife.ui.home

import androidx.navigation.fragment.findNavController
import com.karrar.betterlife.R
import com.karrar.betterlife.databinding.FragmentHomeBinding
import com.karrar.betterlife.ui.base.BaseFragment
import com.karrar.betterlife.util.EventObserve

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    override val layoutIdFragment = R.layout.fragment_home
    override val viewModelClass = HomeViewModel::class.java
}