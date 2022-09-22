package com.karrar.betterlife.ui.statistics

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.karrar.betterlife.R
import com.karrar.betterlife.databinding.FragmentStatisticsBinding
import com.karrar.betterlife.ui.Charts
import com.karrar.betterlife.ui.base.BaseFragment

class StatisticsFragment : BaseFragment<FragmentStatisticsBinding, StatisticsViewModel>() {
    override val layoutIdFragment = R.layout.fragment_statistics
    override val viewModelClass = StatisticsViewModel::class.java

}