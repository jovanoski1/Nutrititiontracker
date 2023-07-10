package com.example.nutrititiontracker.modules

import com.example.nutrititiontracker.data.datasources.local.db.Database
import com.example.nutrititiontracker.data.datasources.remote.MealService
import com.example.nutrititiontracker.data.repository.MealRepository
import com.example.nutrititiontracker.data.repository.MealRepositoryImpl
import com.example.nutrititiontracker.presentation.viewmodel.MealsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mealModule = module {

    viewModel{ MealsViewModel(mealRepository = get()) }

    single<MealService> { create(retrofit = get()) }

    single<MealRepository> { MealRepositoryImpl(get(), get()) }

    single{ get<Database>().getMealDao() }

}