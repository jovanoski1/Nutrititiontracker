package com.example.nutrititiontracker.data.local.datasources

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nutrititiontracker.data.local.models.UserEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
abstract class UserDao {

    @Query("SELECT * FROM users WHERE username=:username AND password=:password")
    abstract fun getUser(username: String, password: String) : Single<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertUser(entity: UserEntity): Completable

}