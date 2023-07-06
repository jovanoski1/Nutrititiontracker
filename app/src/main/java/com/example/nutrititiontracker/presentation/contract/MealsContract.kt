package com.example.nutrititiontracker.presentation.contract

import androidx.lifecycle.LiveData
import com.example.nutrititiontracker.presentation.view.states.CategoriesState
import com.example.nutrititiontracker.presentation.view.states.MealsState

interface MealsContract {
    interface ViewModel {
        val mealState: LiveData<MealsState>

        fun fetchAllMealsByFirstLetter()
    }
}