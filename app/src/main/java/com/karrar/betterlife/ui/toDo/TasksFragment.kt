package com.karrar.betterlife.ui.toDo

import com.karrar.betterlife.R
import com.karrar.betterlife.databinding.FragmentTasksBinding
import com.karrar.betterlife.ui.base.BaseFragment

class TasksFragment : BaseFragment<FragmentTasksBinding, TasksViewModel>() {
    override val layoutIdFragment = R.layout.fragment_tasks
    override val viewModelClass = TasksViewModel::class.java

    override fun onStart() {
        super.onStart()
        setToDoAdapter()
    }

    private fun setToDoAdapter() {
        val adapter = TasksAdapter(mutableListOf(), viewModel)
        binding.recyclerToDo.adapter = adapter
    }

}