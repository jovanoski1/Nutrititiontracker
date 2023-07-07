package com.example.nutrititiontracker.presentation.contract

import androidx.lifecycle.LiveData
import com.example.nutrititiontracker.data.models.IngredientsResponse
import com.example.nutrititiontracker.data.models.UserEntity
import com.example.nutrititiontracker.presentation.view.states.AreasState
import com.example.nutrititiontracker.presentation.view.states.CategoriesState
import com.example.nutrititiontracker.presentation.view.states.IngredientsState

interface CategoriesContract {

    interface ViewModel {
        val categoriesState: LiveData<CategoriesState>
        val areasState: LiveData<AreasState>
        val ingredientsState: LiveData<IngredientsState>

        fun fetchAllCategories()
        fun fetchAllAreas()
        fun fetchAllIngredients()
    }
}