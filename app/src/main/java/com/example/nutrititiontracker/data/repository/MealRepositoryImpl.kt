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

    override fun fetchMealsByFirstLetter(c:Char): Observable<Resource<List<MealResponse>>> {
        return mealService
            .getMealsByFirstLetter(c)
            .map {
                Resource.Success(it.meals)
            }
    }

    override fun fetchMealsByCategory(c: String): Observable<Resource<List<MealResponse>>> {
        return mealService
            .getMealsByCategory(c)
            .map {
                Resource.Success(it.meals)
            }
    }

    override fun fetchMealsByMainIngredient(c: String): Observable<Resource<List<MealResponse>>> {
        return mealService
            .getMealsByMainIngredient(c)
            .map {
                Resource.Success(it.meals)
            }
    }

    override fun fetchMealsByArea(c: String): Observable<Resource<List<MealResponse>>> {
        return mealService
            .getMealsByArea(c)
            .map {
                Resource.Success(it.meals)
            }
    }

    override fun fetchMealByName(c: String): Observable<Resource<List<MealResponse>>> {
        return mealService
            .getMealByName(c)
            .map {
                Resource.Success(it.meals)
            }
    }
}