package com.example.nutrititiontracker.data.repository


import com.example.nutrititiontracker.data.datasources.remote.CategoryService
import com.example.nutrititiontracker.data.models.*
import io.reactivex.Observable

class CategoryRepositoryImpl(
    private val categoryService: CategoryService
) : CategoryRepository{

    override fun fetchAllCategories(): Observable<Resource<List<CategoryResponse>>> {
        return categoryService
            .getAllCategories()
//            .doOnNext{
//                val categories = it
//            }
            .map{
                Resource.Success(it.categories)
            }
    }

    override fun fetchAllAreas(): Observable<Resource<List<AreaResponse>>> {
        return categoryService.getAllAreas()
            .map{
                Resource.Success(it.meals)
            }
    }

    override fun fetchAllIngredients(): Observable<Resource<List<IngredientResponse>>> {
        return categoryService.getAllIngredients()
            .map{
                Resource.Success(it.meals)
            }
    }


}