package com.example.nutrititiontracker.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.nutrititiontracker.R
import com.example.nutrititiontracker.databinding.ActivityMainBinding
import com.example.nutrititiontracker.presentation.contract.CategoriesContract
import com.example.nutrititiontracker.presentation.view.fragments.*
import com.example.nutrititiontracker.presentation.view.states.CategoriesState
import com.example.nutrititiontracker.presentation.viewmodel.CategoriesViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val categoriesViewModel: CategoriesContract.ViewModel by viewModel<CategoriesViewModel>()

    private lateinit var bottomNav: BottomNavigationView

    private var categoryMealListToggler: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        loadFragment(CategoriesFragment())
        bottomNav = binding.bottomNavigation as BottomNavigationView
        bottomNav.setOnItemSelectedListener {
            when (it.itemId){
                R.id.categoryPage -> {
                    if(!categoryMealListToggler) loadFragment(CategoriesFragment())
                    else loadFragment(MealListFragment())
                    true
                }
                R.id.filterPage ->{
                    loadFragment(FilterFragment())
                    true
                }
                R.id.myMealsPage ->{
                    loadFragment(MyMealsFragment())
                    true
                }
                R.id.planPage -> {
                    loadFragment(MealPlanFragment())
                    true
                }
                else -> {
                    true
                }
            }
        }
        categoriesViewModel.fetchAllCategories()

    }

    fun toggleCategoryMealList(){
        categoryMealListToggler = true
        bottomNav.menu.findItem(R.id.categoryPage).title = "Meal list"
    }
    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}