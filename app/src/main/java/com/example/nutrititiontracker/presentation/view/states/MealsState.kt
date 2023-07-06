package com.example.nutrititiontracker.presentation.view.states

import com.example.nutrititiontracker.data.models.CategoriesResponse
import com.example.nutrititiontracker.data.models.CategoryResponse
import com.example.nutrititiontracker.data.models.MealResponse

sealed class MealsState {
    object Loading: MealsState()
    object DataFetched: MealsState()
    data class Success(val meals: List<MealResponse>): MealsState()
    data class Error(val message: String): MealsState()
}