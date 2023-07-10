package com.example.nutrititiontracker.presentation.view.activities

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.nutrititiontracker.data.models.MealResponse
import com.example.nutrititiontracker.databinding.ActivityMealDetailsBinding
import com.example.nutrititiontracker.presentation.contract.MealsContract
import com.example.nutrititiontracker.presentation.view.states.MealDetailState
import com.example.nutrititiontracker.presentation.view.states.MealsState
import com.example.nutrititiontracker.presentation.viewmodel.MealsViewModel
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.min

class MealDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealDetailsBinding
    private val mealsViewModel: MealsContract.ViewModel by viewModel<MealsViewModel>()
    private var meal: MealResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMealDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObservers()
        initListeners()

        val extras = intent.extras
        if (extras!=null){
            val meal = extras.getSerializable("selectedMeal") as MealResponse
            mealsViewModel.fetchMealById(meal.idMeal)
        }
    }

    private fun initListeners() {
        binding.saveMealBtn.setOnClickListener{
            val i = Intent(this, SaveMealActivity::class.java)
            i.putExtra("mealToSave", meal)
            startActivity(i)
        }
    }

    private fun initObservers(){
        mealsViewModel.mealDetailState.observe(this, Observer {
            when(it){
                is MealDetailState.Success ->{
                    binding.loadingPbMl.isVisible = false
                    binding.content.isVisible = true
//                    mealsAdapter.submitList(it.meals)
                    println(it.meals)
                    meal = it.meals
                    initUi()
                }
                is MealDetailState.Loading ->{
                    binding.loadingPbMl.isVisible = true
                    binding.content.isVisible = false
                }
                else -> {
                    println("CEKAJ Meals $it")
                    binding.loadingPbMl.isVisible = false
                    binding.content.isVisible = true
                }
            }
        })
    }

    private fun initUi(){
        binding.mealNameTv.text = meal?.strMeal ?: ""
        binding.categoryTv.text = meal?.strCategory ?: "Not available"
        binding.areaTv.text = meal?.strArea ?: "Not available"
        binding.tagsTv.text = meal?.strTags ?: "Not available"
        binding.instructionsTv.text = meal?.strInstructions ?: "Not available"
        Picasso.get().load(meal!!.strMealThumb).into(binding.mealIv)
        binding.instructionsTv.movementMethod = ScrollingMovementMethod.getInstance()


        binding.ingredint1Tv.text = meal?.strIngredient1 ?: ""
        binding.ingredint2Tv.text = meal?.strIngredient2 ?: ""
        binding.ingredint3Tv.text = meal?.strIngredient3 ?: ""
        binding.ingredint4Tv.text = meal?.strIngredient4 ?: ""
        binding.ingredint5Tv.text = meal?.strIngredient5 ?: ""

        binding.ingredint6Tv.text = meal?.strIngredient6 ?: ""
        binding.ingredint7Tv.text = meal?.strIngredient7 ?: ""
        binding.ingredint8Tv.text = meal?.strIngredient8 ?: ""
        binding.ingredint9Tv.text = meal?.strIngredient9 ?: ""
        binding.ingredint10Tv.text = meal?.strIngredient10 ?: ""

        binding.ingredint11Tv.text = meal?.strIngredient11 ?: ""
        binding.ingredint12Tv.text = meal?.strIngredient12 ?: ""
        binding.ingredint13Tv.text = meal?.strIngredient13 ?: ""
        binding.ingredint14Tv.text = meal?.strIngredient14 ?: ""
        binding.ingredint15Tv.text = meal?.strIngredient15 ?: ""

        binding.ingredint16Tv.text = meal?.strIngredient16 ?: ""
        binding.ingredint17Tv.text = meal?.strIngredient17 ?: ""
        binding.ingredint18Tv.text = meal?.strIngredient18 ?: ""
        binding.ingredint19Tv.text = meal?.strIngredient19 ?: ""
        binding.ingredint20Tv.text = meal?.strIngredient20 ?: ""

        binding.measure1Tv.text = meal?.strMeasure1 ?: ""
        binding.measure2Tv.text = meal?.strMeasure2 ?: ""
        binding.measure3Tv.text = meal?.strMeasure3 ?: ""
        binding.measure4Tv.text = meal?.strMeasure4 ?: ""
        binding.measure5Tv.text = meal?.strMeasure5 ?: ""

        binding.measure6Tv.text = meal?.strMeasure6 ?: ""
        binding.measure7Tv.text = meal?.strMeasure7 ?: ""
        binding.measure8Tv.text = meal?.strMeasure8 ?: ""
        binding.measure9Tv.text = meal?.strMeasure9 ?: ""
        binding.measure10Tv.text = meal?.strMeasure10 ?: ""

        binding.measure11Tv.text = meal?.strMeasure11 ?: ""
        binding.measure12Tv.text = meal?.strMeasure12 ?: ""
        binding.measure13Tv.text = meal?.strMeasure13 ?: ""
        binding.measure14Tv.text = meal?.strMeasure14 ?: ""
        binding.measure15Tv.text = meal?.strMeasure15 ?: ""

        binding.measure16Tv.text = meal?.strMeasure16 ?: ""
        binding.measure17Tv.text = meal?.strMeasure17 ?: ""
        binding.measure18Tv.text = meal?.strMeasure18 ?: ""
        binding.measure19Tv.text = meal?.strMeasure19 ?: ""
        binding.measure20Tv.text = meal?.strMeasure20 ?: ""

    }
}