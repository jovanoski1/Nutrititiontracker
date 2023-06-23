package com.example.nutrititiontracker.modules

import com.example.nutrititiontracker.data.local.db.Database
import com.example.nutrititiontracker.data.local.repository.UserRepository
import com.example.nutrititiontracker.data.local.repository.UserRepositoryImpl
import com.example.nutrititiontracker.presentation.view.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userModule = module {

    viewModel{MainViewModel(userRepository = get())}

    single<UserRepository> {UserRepositoryImpl(get())}

    single { get<Database>().getUserDao() }
}