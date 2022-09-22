package com.karrar.betterlife.ui.tasks

import androidx.navigation.fragment.findNavController
import com.karrar.betterlife.R
import com.karrar.betterlife.databinding.FragmentTasksBinding
import com.karrar.betterlife.ui.TasksAdapter
import com.karrar.betterlife.ui.base.BaseFragment
import com.karrar.betterlife.util.EventObserve

class TasksFragment : BaseFragment<FragmentTasksBinding, TasksViewModel>() {
    override val layoutIdFragment = R.layout.fragment_tasks
    override val viewModelClass = TasksViewModel::class.java

    override fun onStart() {
        super.onStart()

        setTasksAdapter()
        observeEvents()
        observeTasks()
    }


    private fun setTasksAdapter() {
        val adapter = TasksAdapter(mutableListOf(), viewModel)
        binding.recyclerTasks.adapter = adapter
    }


    private fun observeEvents() {
        viewModel.onCLickFloatingButtonEvent.observe(viewLifecycleOwner, EventObserve {
            if (it) navigateToHabitAdditionDialog()
        })
    }

    private fun navigateToHabitAdditionDialog() {
        findNavController().navigate(
            TasksFragmentDirections.actionToDoFragmentToTaskAdditionDialog()
        )
    }


    private fun observeTasks() {
        viewModel.tasks.observe(this) { viewModel.checkIfEmptyList() }
    }

}