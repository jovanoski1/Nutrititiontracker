package com.example.nutrititiontracker.data.repository

import com.example.nutrititiontracker.data.models.*
import io.reactivex.Completable
import io.reactivex.Observable

interface MealRepository {

    fun fetchMealsByFirstLetter(c:Char): Observable<Resource<List<MealResponse>>>

    fun fetchMealsByCategory(c:String): Observable<Resource<List<MealResponse>>>

    fun fetchMealsByMainIngredient(c:String): Observable<Resource<List<MealResponse>>>

    fun fetchMealsByArea(c:String): Observable<Resource<List<MealResponse>>>

    fun fetchMealByName(c:String): Observable<Resource<List<MealResponse>>>

    fun fetchMealById(id:Long): Observable<Resource<MealResponse>>

    fun getAllMealsForUser(id:Long): Observable<List<MealEntity>>

    fun insertMeal(mealEntity: MealEntity): Completable

    fun deleteMeal(mealEntity: MealEntity): Completable

    fun updateMeal(mealEntity: MealEntity): Completable
}