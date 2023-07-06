package com.example.nutrititiontracker.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class MealsResponse(
    val meals: List<MealResponse>
)