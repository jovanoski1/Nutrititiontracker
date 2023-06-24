package com.example.nutrititiontracker.presentation.contract

import androidx.lifecycle.LiveData
import com.example.nutrititiontracker.data.models.CategoriesResponse
import com.example.nutrititiontracker.data.models.UserEntity
import com.example.nutrititiontracker.presentation.view.states.CategoriesState

interface MainContract {

    interface ViewModel {
        val loggedUser: LiveData<UserEntity>
        val categories: LiveData<List<CategoriesResponse>>
        val categoriesState: LiveData<CategoriesState>

        fun fetchAllCategories()
        fun insertUser(userEntity: UserEntity)
        fun getUser(username: String, password: String)
    }
}