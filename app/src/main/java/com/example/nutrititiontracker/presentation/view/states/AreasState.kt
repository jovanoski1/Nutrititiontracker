package com.example.nutrititiontracker.presentation.view.states

import com.example.nutrititiontracker.data.models.AreaResponse
import com.example.nutrititiontracker.data.models.CategoriesResponse
import com.example.nutrititiontracker.data.models.CategoryResponse

sealed class AreasState {
    object Loading: AreasState()
    object DataFetched: AreasState()
    data class Success(val areas: List<AreaResponse>): AreasState()
    data class Error(val message: String): AreasState()
}