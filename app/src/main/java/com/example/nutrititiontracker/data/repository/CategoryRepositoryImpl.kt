package com.example.nutrititiontracker.data.repository


import com.example.nutrititiontracker.data.datasources.remote.CategoryService
import com.example.nutrititiontracker.data.models.CategoriesResponse
import com.example.nutrititiontracker.data.models.CategoryResponse
import com.example.nutrititiontracker.data.models.Resource
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


}