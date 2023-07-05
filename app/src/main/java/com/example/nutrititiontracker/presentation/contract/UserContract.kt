package com.example.nutrititiontracker.presentation.contract

import androidx.lifecycle.LiveData
import com.example.nutrititiontracker.data.models.UserEntity

interface UserContract {
    interface ViewModel{
        val loggedUser: LiveData<UserEntity>

        fun insertUser(userEntity: UserEntity)
        fun getUser(username: String, password: String)
    }
}