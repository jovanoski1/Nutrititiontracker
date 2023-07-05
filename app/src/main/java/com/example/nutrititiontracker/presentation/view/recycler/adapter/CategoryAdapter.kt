package com.example.nutrititiontracker.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.nutrititiontracker.data.models.CategoriesResponse
import com.example.nutrititiontracker.data.models.CategoryResponse
import com.example.nutrititiontracker.databinding.CategoryListItemBinding
import com.example.nutrititiontracker.presentation.view.recycler.diff.CategoryDiffCallback
import com.example.nutrititiontracker.presentation.view.recycler.viewholder.CategoryViewHolder

class CategoryAdapter: ListAdapter<CategoryResponse, CategoryViewHolder>(CategoryDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemBinding = CategoryListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}