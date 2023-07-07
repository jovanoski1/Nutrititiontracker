package com.example.nutrititiontracker.data.repository

import com.example.nutrititiontracker.data.models.CategoryResponse
import com.example.nutrititiontracker.data.models.MealResponse
import com.example.nutrititiontracker.data.models.MealsResponse
import com.example.nutrititiontracker.data.models.Resource
import io.reactivex.Observable

interface MealRepository {

    fun fetchMealsByFirstLetter(c:Char): Observable<Resource<List<MealResponse>>>

    fun fetchMealsByCategory(c:String): Observable<Resource<List<MealResponse>>>

    fun fetchMealsByMainIngredient(c:String): Observable<Resource<List<MealResponse>>>

    fun fetchMealsByArea(c:String): Observable<Resource<List<MealResponse>>>

    fun fetchMealByName(c:String): Observable<Resource<List<MealResponse>>>
}