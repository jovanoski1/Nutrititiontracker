package com.example.nutrititiontracker.modules

import com.example.nutrititiontracker.data.datasources.remote.CategoryService
import com.example.nutrititiontracker.data.repository.CategoryRepository
import com.example.nutrititiontracker.data.repository.CategoryRepositoryImpl
import com.example.nutrititiontracker.presentation.viewmodel.CategoriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val categoriesModule = module{

    viewModel{ CategoriesViewModel(categoryRepository = get()) }

    single<CategoryRepository> { CategoryRepositoryImpl(get()) }

    single<CategoryService> { create(retrofit = get()) }
}