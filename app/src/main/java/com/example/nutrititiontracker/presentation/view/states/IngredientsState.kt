package com.example.nutrititiontracker.presentation.view.states

import com.example.nutrititiontracker.data.models.CategoriesResponse
import com.example.nutrititiontracker.data.models.CategoryResponse
import com.example.nutrititiontracker.data.models.IngredientResponse

sealed class IngredientsState {
    object Loading: IngredientsState()
    object DataFetched: IngredientsState()
    data class Success(val ingredients: List<IngredientResponse>): IngredientsState()
    data class Error(val message: String): IngredientsState()
}