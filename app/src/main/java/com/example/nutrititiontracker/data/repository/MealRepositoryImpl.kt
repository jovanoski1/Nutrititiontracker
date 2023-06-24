package com.example.nutrititiontracker.data.repository


import com.example.nutrititiontracker.data.datasources.remote.MealService
import com.example.nutrititiontracker.data.models.CategoriesResponse
import com.example.nutrititiontracker.data.models.Resource
import io.reactivex.Observable

class MealRepositoryImpl(
    private val mealService: MealService
) : MealRepository{

    override fun fetchAllCategories(): Observable<Resource<List<CategoriesResponse>>> {
        return mealService
            .getAllCategories()
//            .doOnNext{
//                val categories = it
//            }
            .map{
                Resource.Success(it)
            }
    }


}