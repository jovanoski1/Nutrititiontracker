package com.example.nutrititiontracker.data.repository

import com.example.nutrititiontracker.data.models.*
import io.reactivex.Observable

interface CategoryRepository {
    fun fetchAllCategories(): Observable<Resource<List<CategoryResponse>>>

    fun fetchAllAreas(): Observable<Resource<List<AreaResponse>>>

    fun fetchAllIngredients(): Observable<Resource<List<IngredientResponse>>>


}