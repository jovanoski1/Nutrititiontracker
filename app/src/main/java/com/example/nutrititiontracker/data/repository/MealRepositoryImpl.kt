package com.example.nutrititiontracker.data.repository

import android.annotation.SuppressLint
import com.example.nutrititiontracker.data.datasources.remote.MealService
import com.example.nutrititiontracker.data.models.CategoryResponse
import com.example.nutrititiontracker.data.models.MealResponse
import com.example.nutrititiontracker.data.models.MealsResponse
import com.example.nutrititiontracker.data.models.Resource
import io.reactivex.Observable

class MealRepositoryImpl(
    private val mealService: MealService
):MealRepository {

    @SuppressLint("CheckResult")
    override fun fetchMealsByFirstLetter(c:Char): Observable<Resource<List<MealResponse>>> {
        return mealService
            .getMealsByFirstLetter(c)
            .map {
                Resource.Success(it.meals)
            }
    }

}