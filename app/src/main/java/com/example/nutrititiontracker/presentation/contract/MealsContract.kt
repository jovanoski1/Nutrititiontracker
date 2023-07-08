package com.example.nutrititiontracker.presentation.contract

import androidx.lifecycle.LiveData
import com.example.nutrititiontracker.presentation.view.states.CategoriesState
import com.example.nutrititiontracker.presentation.view.states.MealDetailState
import com.example.nutrititiontracker.presentation.view.states.MealsState

interface MealsContract {
    interface ViewModel {
        val mealState: LiveData<MealsState>
        val mealDetailState: LiveData<MealDetailState>

        fun fetchAllMealsByFirstLetter(c:Char)
        fun fetchAllMealsByCategory(categoryName:String)

        fun fetchAllMealsByMainIngredient(mainIngredient:String)
        fun fetchAllMealsByArea(area:String)

        fun fetchMealByName(name:String)
        fun fetchMealById(id:Long)

        fun sortAsc()
        fun sortDesc()
    }
}