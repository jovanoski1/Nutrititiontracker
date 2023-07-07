package com.example.nutrititiontracker.data.datasources.remote

import com.example.nutrititiontracker.data.models.MealResponse
import com.example.nutrititiontracker.data.models.MealsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MealService {

    @GET("search.php?")
    fun getMealsByFirstLetter(@Query("f") f:Char): Observable<MealsResponse>

    @GET("filter.php?")
    fun getMealsByCategory(@Query("c") c:String): Observable<MealsResponse>

    @GET("filter.php?")
    fun getMealsByMainIngredient(@Query("i") i:String): Observable<MealsResponse>

    @GET("filter.php?")
    fun getMealsByArea(@Query("a") a:String): Observable<MealsResponse>

    @GET("search.php?")
    fun getMealByName(@Query("s") s:String): Observable<MealsResponse>
}