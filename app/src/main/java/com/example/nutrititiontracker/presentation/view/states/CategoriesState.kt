package com.example.nutrititiontracker.presentation.view.states

import com.example.nutrititiontracker.data.models.CategoriesResponse

sealed class CategoriesState {
    object Loading: CategoriesState()
    object DataFetched: CategoriesState()
    data class Success(val categories: List<CategoriesResponse>): CategoriesState()
    data class Error(val message: String): CategoriesState()
}