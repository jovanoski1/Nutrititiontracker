package com.example.nutrititiontracker.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import com.example.nutrititiontracker.data.models.MealEntity

class MyMealDiffCallback : DiffUtil.ItemCallback<MealEntity>() {
    override fun areItemsTheSame(oldItem: MealEntity, newItem: MealEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MealEntity, newItem: MealEntity): Boolean {
        return oldItem.id == newItem.id
    }
}