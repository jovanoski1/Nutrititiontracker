package com.example.nutrititiontracker.presentation.view.activities

import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.nutrititiontracker.R
import com.example.nutrititiontracker.data.models.MealEntity
import com.example.nutrititiontracker.databinding.ActivityMainBinding
import com.example.nutrititiontracker.presentation.contract.CategoriesContract
import com.example.nutrititiontracker.presentation.view.fragments.*
import com.example.nutrititiontracker.presentation.viewmodel.CategoriesViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val categoriesViewModel: CategoriesContract.ViewModel by viewModel<CategoriesViewModel>()

    private lateinit var bottomNav: BottomNavigationView

    private var categoryMealListToggler: Boolean = false
    private val sharedPreferences: SharedPreferences by inject()

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

    override fun onResume() {
        super.onResume()
        val data: Uri? = intent?.data

        println("DATAAAAA")
        if (data?.query != null) {
//                val bundle = Bundle().apply {
//                    putSerializable("mealPlanShared", data.query)
//                }
            sharedPreferences.edit().putString("mealPlanShared", data.query).apply()
//                val transaction = supportFragmentManager.beginTransaction()
//                val fragment = MealPlanFragment()
//                fragment.arguments = bundle
////                transaction.replace(R.id.container, fragment)
//                transaction.commit()
                bottomNav.selectedItemId = R.id.planPage
//            }
        }
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