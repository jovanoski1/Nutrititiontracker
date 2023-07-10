package com.example.nutrititiontracker.presentation.view.recycler.listeners

import com.example.nutrititiontracker.data.models.CategoryResponse
import com.example.nutrititiontracker.data.models.MealEntity
import com.example.nutrititiontracker.data.models.MealResponse

interface MyMealClickListener {

    fun onEditClick(mealEntity: MealEntity)
    fun onDeleteClick(mealEntity: MealEntity)

}