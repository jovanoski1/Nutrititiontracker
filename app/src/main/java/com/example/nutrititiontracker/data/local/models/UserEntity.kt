package com.example.nutrititiontracker.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity (

    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var username: String,
    var password: String,
)