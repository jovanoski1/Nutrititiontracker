package com.example.nutrititiontracker.data.repository

import com.example.nutrititiontracker.data.models.CategoryResponse
import com.example.nutrititiontracker.data.models.MealResponse
import com.example.nutrititiontracker.data.models.MealsResponse
import com.example.nutrititiontracker.data.models.Resource
import io.reactivex.Observable

interface MealRepository {

    fun fetchMealsByFirstLetter(c:Char): Observable<Resource<List<MealResponse>>>
}