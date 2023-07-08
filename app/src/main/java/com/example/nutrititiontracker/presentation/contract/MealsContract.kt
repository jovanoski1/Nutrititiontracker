package com.example.nutrititiontracker.presentation.contract

import androidx.lifecycle.LiveData
import com.example.nutrititiontracker.presentation.view.states.CategoriesState
import com.example.nutrititiontracker.presentation.view.states.MealsState

interface MealsContract {
    interface ViewModel {
        val mealState: LiveData<MealsState>

        fun fetchAllMealsByFirstLetter(c:Char)
        fun fetchAllMealsByCategory(categoryName:String)

        fun fetchAllMealsByMainIngredient(mainIngredient:String)
        fun fetchAllMealsByArea(area:String)

        fun fetchMealByName(name:String)

        fun sortAsc()
        fun sortDesc()
    }
}