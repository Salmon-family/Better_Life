package com.karrar.betterlife.ui.home

import com.karrar.betterlife.R
import com.karrar.betterlife.data.Category
import com.karrar.betterlife.ui.base.BaseAdapter

class CategoryAdapter(items: List<Category>) : BaseAdapter<Category>(items, null) {
    override val layoutID: Int = R.layout.item_category
}