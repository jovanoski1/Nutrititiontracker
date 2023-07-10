package com.example.nutrititiontracker.data.repository

import android.annotation.SuppressLint
import com.example.nutrititiontracker.data.datasources.local.MealDao
import com.example.nutrititiontracker.data.datasources.remote.MealService
import com.example.nutrititiontracker.data.models.*
import io.reactivex.Completable
import io.reactivex.Observable

class MealRepositoryImpl(
    private val mealService: MealService,
    private val localMeal: MealDao
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

    override fun fetchMealById(id: Long): Observable<Resource<MealResponse>> {
        return mealService
            .getMealById(id)
            .map {
                Resource.Success(it.meals[0])
            }
    }

    override fun getAllMealsForUser(id: Long): Observable<List<MealEntity>> {
        return localMeal
            .getMealsForUsr(id)
    }

    override fun insertMeal(mealEntity: MealEntity): Completable {
        return localMeal.insertMeal(mealEntity)
    }

    override fun deleteMeal(mealEntity: MealEntity): Completable {
        return localMeal.deleteMeal(mealEntity)
    }
}