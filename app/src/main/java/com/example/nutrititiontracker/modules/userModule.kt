package com.example.nutrititiontracker.modules

import com.example.nutrititiontracker.data.datasources.local.db.Database
import com.example.nutrititiontracker.data.repository.UserRepository
import com.example.nutrititiontracker.data.repository.UserRepositoryImpl
import com.example.nutrititiontracker.presentation.viewmodel.CategoriesViewModel
import com.example.nutrititiontracker.presentation.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userModule = module {

    viewModel{ UserViewModel(userRepository = get()) }

    single<UserRepository> { UserRepositoryImpl(get()) }

    single { get<Database>().getUserDao() }
}