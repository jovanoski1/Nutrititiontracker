package com.example.nutrititiontracker.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoryResponse(
    val idCategory:Long,
    val strCategory: String,
    val strCategoryThumb:String,
    val strCategoryDescription: String,
)