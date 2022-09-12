package com.karrar.betterlife.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {
    val checkedBtnObs = MutableLiveData<Int>()
}