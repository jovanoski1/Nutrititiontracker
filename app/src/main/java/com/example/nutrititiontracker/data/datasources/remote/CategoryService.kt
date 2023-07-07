package com.example.nutrititiontracker.data.datasources.remote

import com.example.nutrititiontracker.data.models.AreasResponse
import com.example.nutrititiontracker.data.models.CategoriesResponse
import com.example.nutrititiontracker.data.models.IngredientsResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface CategoryService {

    @GET("categories.php")
    fun getAllCategories() : Observable<CategoriesResponse>

    @GET("list.php?a=list")
    fun getAllAreas(): Observable<AreasResponse>
    @GET("list.php?i=list")
    fun getAllIngredients(): Observable<IngredientsResponse>
}