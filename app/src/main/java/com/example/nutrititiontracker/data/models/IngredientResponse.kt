package com.example.nutrititiontracker.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class IngredientResponse(
    var idIngredient:Long,
    var strIngredient:String,
    var strDescription:String?,
)