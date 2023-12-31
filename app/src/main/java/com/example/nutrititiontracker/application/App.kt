package com.example.nutrititiontracker.application

import android.app.Application
import com.example.nutrititiontracker.modules.categoriesModule
import com.example.nutrititiontracker.modules.coreModule
import com.example.nutrititiontracker.modules.mealModule
import com.example.nutrititiontracker.modules.userModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        init()
        println("USAOOOO")
    }

    private fun init() {
        val modules = listOf(
            coreModule,
            userModule,
            mealModule,
            categoriesModule
        )
        startKoin{
            androidLogger(Level.ERROR)
            androidContext(this@App)
            androidFileProperties()
            fragmentFactory()
            modules(modules)
        }

    }
}