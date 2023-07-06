package com.example.nutrititiontracker.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.nutrititiontracker.data.models.MealResponse
import com.example.nutrititiontracker.databinding.CategoryListItemBinding
import com.example.nutrititiontracker.databinding.MealListItemBinding
import com.example.nutrititiontracker.presentation.view.recycler.diff.MealDiffCallback
import com.example.nutrititiontracker.presentation.view.recycler.viewholder.MealViewHolder

class MealAdapter: ListAdapter<MealResponse, MealViewHolder>(MealDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val itemBinding = MealListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}