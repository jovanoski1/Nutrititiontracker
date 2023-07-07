package com.example.nutrititiontracker.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class IngredientsResponse(
    val meals: List<IngredientResponse>
)