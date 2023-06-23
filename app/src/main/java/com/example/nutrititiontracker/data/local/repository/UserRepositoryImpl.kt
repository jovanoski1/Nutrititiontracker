package com.example.nutrititiontracker.data.local.repository

import com.example.nutrititiontracker.data.local.datasources.UserDao
import com.example.nutrititiontracker.data.local.models.UserEntity
import io.reactivex.Completable
import io.reactivex.Observable

class UserRepositoryImpl(private val userDao: UserDao): UserRepository {
    override fun getUser(username: String, password: String): Observable<List<UserEntity>> {
        return userDao.getUser(username, password).map {
            it.map {
                UserEntity(it.id, it.username, it.password)
            }
        }
    }

    override fun insertUser(userEntity: UserEntity): Completable {
        return userDao.insertUser(userEntity)
    }
}