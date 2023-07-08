package com.example.nutrititiontracker.presentation.view.recycler.listeners

import com.example.nutrititiontracker.data.models.CategoryResponse
import com.example.nutrititiontracker.data.models.MealResponse

interface MealClickListener {

    fun onItemClick(mealResponse: MealResponse)
}