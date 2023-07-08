package com.example.nutrititiontracker.presentation.view.states

import com.example.nutrititiontracker.data.models.CategoriesResponse
import com.example.nutrititiontracker.data.models.CategoryResponse
import com.example.nutrititiontracker.data.models.MealResponse

sealed class MealDetailState {
    object Loading: MealDetailState()
    object DataFetched: MealDetailState()
    data class Success(val meals: MealResponse): MealDetailState()
    data class Error(val message: String): MealDetailState()
}