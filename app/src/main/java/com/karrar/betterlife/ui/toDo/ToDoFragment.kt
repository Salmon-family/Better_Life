package com.karrar.betterlife.ui.toDo

import com.karrar.betterlife.R
import com.karrar.betterlife.databinding.FragmentToDoBinding
import com.karrar.betterlife.ui.base.BaseFragment

class ToDoFragment : BaseFragment<FragmentToDoBinding, ToDoViewModel>() {
    override val layoutIdFragment = R.layout.fragment_to_do
    override val viewModelClass = ToDoViewModel::class.java
}