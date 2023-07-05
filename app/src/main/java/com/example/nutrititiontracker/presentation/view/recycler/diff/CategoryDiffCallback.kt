package com.example.nutrititiontracker.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import com.example.nutrititiontracker.data.models.CategoriesResponse
import com.example.nutrititiontracker.data.models.CategoryResponse

class CategoryDiffCallback : DiffUtil.ItemCallback<CategoryResponse>() {
    override fun areItemsTheSame(
        oldItem: CategoryResponse,
        newItem: CategoryResponse
    ): Boolean {
       return oldItem.idCategory == newItem.idCategory
    }

    override fun areContentsTheSame(
        oldItem: CategoryResponse,
        newItem: CategoryResponse
    ): Boolean {
        return oldItem.idCategory == newItem.idCategory
    }
}