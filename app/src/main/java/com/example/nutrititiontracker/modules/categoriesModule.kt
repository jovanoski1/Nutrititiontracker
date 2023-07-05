package com.example.nutrititiontracker.modules

import com.example.nutrititiontracker.presentation.viewmodel.CategoriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val categoriesModule = module{

    viewModel{ CategoriesViewModel(mealRepository = get()) }

}