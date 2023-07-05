package com.example.nutrititiontracker.data.datasources.remote

import com.example.nutrititiontracker.data.models.CategoriesResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface MealService {

    @GET("categories.php")
    fun getAllCategories() : Observable<List<CategoriesResponse>>
}