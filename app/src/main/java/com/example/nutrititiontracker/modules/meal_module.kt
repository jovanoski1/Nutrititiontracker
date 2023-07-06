package com.example.nutrititiontracker.modules

import com.example.nutrititiontracker.data.datasources.remote.CategoryService
import com.example.nutrititiontracker.data.datasources.remote.MealService
import com.example.nutrititiontracker.data.repository.CategoryRepository
import com.example.nutrititiontracker.data.repository.CategoryRepositoryImpl
import com.example.nutrititiontracker.data.repository.MealRepository
import com.example.nutrititiontracker.data.repository.MealRepositoryImpl
import com.example.nutrititiontracker.presentation.viewmodel.CategoriesViewModel
import com.example.nutrititiontracker.presentation.viewmodel.MealsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mealModule = module {

    viewModel{ MealsViewModel(mealRepository = get()) }

    single<MealService> { create(retrofit = get()) }

    single<MealRepository> { MealRepositoryImpl(get()) }

}