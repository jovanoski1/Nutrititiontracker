package com.example.nutrititiontracker.presentation.contract

import androidx.lifecycle.LiveData
import com.example.nutrititiontracker.data.models.UserEntity
import com.example.nutrititiontracker.presentation.view.states.CategoriesState

interface CategoriesContract {

    interface ViewModel {
        val categoriesState: LiveData<CategoriesState>

        fun fetchAllCategories()
    }
}