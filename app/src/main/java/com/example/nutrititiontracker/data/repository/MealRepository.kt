package com.example.nutrititiontracker.data.repository

import com.example.nutrititiontracker.data.models.CategoriesResponse
import com.example.nutrititiontracker.data.models.Resource
import io.reactivex.Observable

interface MealRepository {
    fun fetchAllCategories(): Observable<Resource<List<CategoriesResponse>>>
}