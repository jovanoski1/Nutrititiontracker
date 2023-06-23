package com.example.nutrititiontracker.data.local.repository

import com.example.nutrititiontracker.data.local.models.UserEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface UserRepository {
    fun getUser(username: String, password: String) : Observable<List<UserEntity>>

    fun insertUser(userEntity: UserEntity): Completable
}