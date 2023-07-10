package com.example.nutrititiontracker.presentation.view.recycler.listeners

import com.example.nutrititiontracker.data.models.MealEntity

interface GridPlanClickListener {
    fun onItemClick(mealEntity:MealEntity, index:Int)
}