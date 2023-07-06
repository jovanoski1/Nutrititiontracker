package com.example.nutrititiontracker.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import com.example.nutrititiontracker.data.models.MealResponse

class MealDiffCallback : DiffUtil.ItemCallback<MealResponse>() {
    override fun areItemsTheSame(oldItem: MealResponse, newItem: MealResponse): Boolean {
        return oldItem.idMeal == newItem.idMeal
    }

    override fun areContentsTheSame(oldItem: MealResponse, newItem: MealResponse): Boolean {
        return oldItem.idMeal == newItem.idMeal
    }
}