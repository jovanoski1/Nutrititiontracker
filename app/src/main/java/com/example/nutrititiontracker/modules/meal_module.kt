package com.example.nutrititiontracker.modules

import com.example.nutrititiontracker.data.datasources.remote.CategoryService
import com.example.nutrititiontracker.data.repository.CategoryRepository
import com.example.nutrititiontracker.data.repository.CategoryRepositoryImpl
import org.koin.dsl.module

val mealModule = module {

    single<CategoryRepository> { CategoryRepositoryImpl(get()) }

    single<CategoryService> { create(retrofit = get()) }
}