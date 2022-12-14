package com.karrar.betterlife.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.karrar.betterlife.BR

abstract class BaseAdapter<T>(
    private var items: List<T>,
    private val listener: BaseInteractionListener
) : RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {

    abstract val layoutID: Int

    fun setItems(newItems: List<T>) {
        val diffResult = DiffUtil.calculateDiff(
            BaseDiffUtil(items, newItems,
                { oldItem, newItem -> areItemsSame(oldItem, newItem) },
                { oldItem, newItem -> areItemsSame(oldItem, newItem) })
        )
        items = newItems
        diffResult.dispatchUpdatesTo(this)
    }

    open fun areItemsSame(oldItem: T, newItem: T): Boolean {
        return oldItem?.equals(newItem) == true
    }

    abstract fun areContentsSame(oldItem: T, newItem: T): Boolean

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), layoutID, parent, false
            )
        )

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (holder is ItemViewHolder) bind(holder, position)
    }

    override fun getItemCount() = items.size

    private fun bind(holder: ItemViewHolder, position: Int) {
        holder.binding.apply {
            setVariable(BR.item, items[position])
            setVariable(BR.listener, listener)
        }
    }

    class ItemViewHolder(val binding: ViewDataBinding) : BaseViewHolder(binding)

    abstract class BaseViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

}

interface BaseInteractionListener