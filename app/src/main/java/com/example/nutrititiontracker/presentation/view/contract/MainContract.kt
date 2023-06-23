package com.example.nutrititiontracker.presentation.view.contract

import androidx.lifecycle.LiveData
import com.example.nutrititiontracker.data.local.models.UserEntity

interface MainContract {

    interface ViewModel {
        val loggedUser: LiveData<UserEntity>

        fun insertUser(userEntity: UserEntity)
        fun getUser(username: String, password: String)
    }
}