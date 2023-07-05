package com.example.nutrititiontracker.modules

import com.example.nutrititiontracker.data.datasources.remote.MealService
import com.example.nutrititiontracker.data.repository.MealRepository
import com.example.nutrititiontracker.data.repository.MealRepositoryImpl
import org.koin.dsl.module

val mealModule = module {

    single<MealRepository> { MealRepositoryImpl(get()) }

    single<MealService> { create(retrofit = get()) }
}