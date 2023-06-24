package com.example.nutrititiontracker.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CategoriesResponse (
    val id:Long,
    val strCategory: String,
    val strCategoryThumb:String,
    val strCategoryDescription: String,
)