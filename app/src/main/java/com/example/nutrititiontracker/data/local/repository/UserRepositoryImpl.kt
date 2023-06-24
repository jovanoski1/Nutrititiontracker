package com.example.nutrititiontracker.data.local.repository

import com.example.nutrititiontracker.data.local.datasources.UserDao
import com.example.nutrititiontracker.data.local.models.UserEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class UserRepositoryImpl(private val userDao: UserDao): UserRepository {
    override fun getUser(username: String, password: String): Single<UserEntity> {
        return userDao.getUser(username, password)
    }

    override fun insertUser(userEntity: UserEntity): Completable {
        return userDao.insertUser(userEntity)
    }
}