package com.example.nutrititiontracker.data.datasources.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nutrititiontracker.data.datasources.local.UserDao
import com.example.nutrititiontracker.data.models.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 2,
    exportSchema = false
)
abstract class Database :RoomDatabase (){
    abstract fun getUserDao(): UserDao
}