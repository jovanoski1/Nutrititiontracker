package com.example.nutrititiontracker.presentation.view.recycler.listeners

import com.example.nutrititiontracker.data.models.MealEntity
import com.example.nutrititiontracker.data.models.MealResponse

interface MealPlanMealClickListener {
    fun onMyMealClick(mealEntity: MealEntity)
    fun onMealClick(mealResponse: MealResponse)
}