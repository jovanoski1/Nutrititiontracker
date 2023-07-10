package com.example.nutrititiontracker.data.datasources.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.nutrititiontracker.data.datasources.local.DateConverter
import com.example.nutrititiontracker.data.datasources.local.MealDao
import com.example.nutrititiontracker.data.datasources.local.UserDao
import com.example.nutrititiontracker.data.models.MealEntity
import com.example.nutrititiontracker.data.models.UserEntity

@Database(
    entities = [UserEntity::class, MealEntity::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class Database :RoomDatabase (){
    abstract fun getUserDao(): UserDao
    abstract fun getMealDao(): MealDao
}