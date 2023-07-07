package com.example.nutrititiontracker.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class AreasResponse(
    val meals: List<AreaResponse>
)