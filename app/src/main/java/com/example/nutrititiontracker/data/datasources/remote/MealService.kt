package com.example.nutrititiontracker.data.datasources.remote

import com.example.nutrititiontracker.data.models.MealsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MealService {

    @GET("search.php?")
    fun getMealsByFirstLetter(@Query("f") f:Char): Observable<MealsResponse>

}