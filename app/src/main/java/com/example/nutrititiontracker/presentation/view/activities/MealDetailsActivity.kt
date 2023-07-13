package com.example.nutrititiontracker.presentation.view.activities

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
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
            println(meal)
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

        val ingredientsTextViews = arrayOf(
            binding.ingredint1Tv,
            binding.ingredint2Tv,
            binding.ingredint3Tv,
            binding.ingredint4Tv,
            binding.ingredint5Tv,
            binding.ingredint6Tv,
            binding.ingredint7Tv,
            binding.ingredint8Tv,
            binding.ingredint9Tv,
            binding.ingredint10Tv,
            binding.ingredint11Tv,
            binding.ingredint12Tv,
            binding.ingredint13Tv,
            binding.ingredint14Tv,
            binding.ingredint15Tv,
            binding.ingredint16Tv,
            binding.ingredint17Tv,
            binding.ingredint18Tv,
            binding.ingredint19Tv,
            binding.ingredint20Tv
        )

        val measuresTextViews = arrayOf(
            binding.measure1Tv,
            binding.measure2Tv,
            binding.measure3Tv,
            binding.measure4Tv,
            binding.measure5Tv,
            binding.measure6Tv,
            binding.measure7Tv,
            binding.measure8Tv,
            binding.measure9Tv,
            binding.measure10Tv,
            binding.measure11Tv,
            binding.measure12Tv,
            binding.measure13Tv,
            binding.measure14Tv,
            binding.measure15Tv,
            binding.measure16Tv,
            binding.measure17Tv,
            binding.measure18Tv,
            binding.measure19Tv,
            binding.measure20Tv
        )

        val ingredients = arrayOf(
            meal?.strIngredient1,
            meal?.strIngredient2,
            meal?.strIngredient3,
            meal?.strIngredient4,
            meal?.strIngredient5,
            meal?.strIngredient6,
            meal?.strIngredient7,
            meal?.strIngredient8,
            meal?.strIngredient9,
            meal?.strIngredient10,
            meal?.strIngredient11,
            meal?.strIngredient12,
            meal?.strIngredient13,
            meal?.strIngredient14,
            meal?.strIngredient15,
            meal?.strIngredient16,
            meal?.strIngredient17,
            meal?.strIngredient18,
            meal?.strIngredient19,
            meal?.strIngredient20
        )

        val measures = arrayOf(
            meal?.strMeasure1,
            meal?.strMeasure2,
            meal?.strMeasure3,
            meal?.strMeasure4,
            meal?.strMeasure5,
            meal?.strMeasure6,
            meal?.strMeasure7,
            meal?.strMeasure8,
            meal?.strMeasure9,
            meal?.strMeasure10,
            meal?.strMeasure11,
            meal?.strMeasure12,
            meal?.strMeasure13,
            meal?.strMeasure14,
            meal?.strMeasure15,
            meal?.strMeasure16,
            meal?.strMeasure17,
            meal?.strMeasure18,
            meal?.strMeasure19,
            meal?.strMeasure20
        )

        for (i in ingredients.indices) {
            ingredientsTextViews[i].text = ingredients[i] ?: ""
            ingredientsTextViews[i].visibility = if (!ingredients[i].isNullOrEmpty() && !ingredients[i].isNullOrBlank()) View.VISIBLE else View.GONE
        }

        for (i in measures.indices) {
            measuresTextViews[i].text = measures[i] ?: ""
            println("mera " + measures[i])
            measuresTextViews[i].visibility = if (!measures[i].isNullOrEmpty() && !measures[i].isNullOrBlank()) View.VISIBLE else View.GONE
        }


//        binding.ingredint1Tv.text = meal?.strIngredient1 ?: ""
//        if (meal!!.strIngredient1 != null) binding.ingredint1Tv.visibility = View.VISIBLE
//        binding.ingredint2Tv.text = meal?.strIngredient2 ?: ""
//        if (meal!!.strIngredient2 != null) binding.ingredint2Tv.visibility = View.VISIBLE
//
//        binding.ingredint3Tv.text = meal?.strIngredient3 ?: ""
//        if (meal!!.strIngredient3 != null) binding.ingredint3Tv.visibility = View.VISIBLE
//
//        binding.ingredint4Tv.text = meal?.strIngredient4 ?: ""
//        if (meal!!.strIngredient4 != null) binding.ingredint4Tv.visibility = View.VISIBLE
//
//        binding.ingredint5Tv.text = meal?.strIngredient5 ?: ""
//        if (meal!!.strIngredient5 != null) binding.ingredint5Tv.visibility = View.VISIBLE
//
//
//        binding.ingredint6Tv.text = meal?.strIngredient6 ?: ""
//        if (meal!!.strIngredient6 != null) binding.ingredint6Tv.visibility = View.VISIBLE
//
//        binding.ingredint7Tv.text = meal?.strIngredient7 ?: ""
//        if (meal!!.strIngredient7 != null) binding.ingredint5Tv.visibility = View.VISIBLE
//
//        binding.ingredint8Tv.text = meal?.strIngredient8 ?: ""
//        if (meal!!.strIngredient8 != null) binding.ingredint8Tv.visibility = View.VISIBLE
//
//        binding.ingredint9Tv.text = meal?.strIngredient9 ?: ""
//        if (meal!!.strIngredient9 != null) binding.ingredint9Tv.visibility = View.VISIBLE
//
//        binding.ingredint10Tv.text = meal?.strIngredient10 ?: ""
//        if (meal!!.strIngredient10 != null) binding.ingredint10Tv.visibility = View.VISIBLE
//
//        binding.ingredint11Tv.text = meal?.strIngredient11 ?: ""
//        if (meal!!.strIngredient11 != null) binding.ingredint11Tv.visibility = View.VISIBLE
//
//        binding.ingredint12Tv.text = meal?.strIngredient12 ?: ""
//        if (meal!!.strIngredient12 != null) binding.ingredint12Tv.visibility = View.VISIBLE
//
//        binding.ingredint13Tv.text = meal?.strIngredient13 ?: ""
//        if (meal!!.strIngredient13 != null) binding.ingredint13Tv.visibility = View.VISIBLE
//
//        binding.ingredint14Tv.text = meal?.strIngredient14 ?: ""
//        if (meal!!.strIngredient14 != null) binding.ingredint14Tv.visibility = View.VISIBLE
//
//        binding.ingredint15Tv.text = meal?.strIngredient15 ?: ""
//        if (meal!!.strIngredient15 != null) binding.ingredint15Tv.visibility = View.VISIBLE
//
//
//        binding.ingredint16Tv.text = meal?.strIngredient16 ?: ""
//        if (meal!!.strIngredient16 != null) binding.ingredint16Tv.visibility = View.VISIBLE
//
//        binding.ingredint17Tv.text = meal?.strIngredient17 ?: ""
//        if (meal!!.strIngredient17 != null) binding.ingredint17Tv.visibility = View.VISIBLE
//
//        binding.ingredint18Tv.text = meal?.strIngredient18 ?: ""
//        if (meal!!.strIngredient18 != null) binding.ingredint18Tv.visibility = View.VISIBLE
//
//        binding.ingredint19Tv.text = meal?.strIngredient19 ?: ""
//        if (meal!!.strIngredient19 != null) binding.ingredint19Tv.visibility = View.VISIBLE
//
//        binding.ingredint20Tv.text = meal?.strIngredient20 ?: ""
//        if (meal!!.strIngredient20 != null) binding.ingredint20Tv.visibility = View.VISIBLE
//
//        binding.measure1Tv.text = meal?.strMeasure1 ?: ""
//        if (meal!!.strMeasure1 != null) binding.measure1Tv.visibility = View.VISIBLE
//        binding.measure2Tv.text = meal?.strMeasure2 ?: ""
//        if (meal!!.strMeasure2 != null) binding.measure2Tv.visibility = View.VISIBLE
//
//        binding.measure3Tv.text = meal?.strMeasure3 ?: ""
//        if (meal!!.strMeasure3 != null) binding.measure3Tv.visibility = View.VISIBLE
//
//        binding.measure4Tv.text = meal?.strMeasure4 ?: ""
//        if (meal!!.strMeasure4 != null) binding.measure4Tv.visibility = View.VISIBLE
//
//        binding.measure5Tv.text = meal?.strMeasure5 ?: ""
//        if (meal!!.strMeasure5 != null) binding.measure5Tv.visibility = View.VISIBLE
//
//
//        binding.measure6Tv.text = meal?.strMeasure6 ?: ""
//        if (meal!!.strMeasure6 != null) binding.measure6Tv.visibility = View.VISIBLE
//
//        binding.measure7Tv.text = meal?.strMeasure7 ?: ""
//        if (meal!!.strMeasure7 != null) binding.measure5Tv.visibility = View.VISIBLE
//
//        binding.measure8Tv.text = meal?.strMeasure8 ?: ""
//        if (meal!!.strMeasure8 != null) binding.measure8Tv.visibility = View.VISIBLE
//
//        binding.measure9Tv.text = meal?.strMeasure9 ?: ""
//        if (meal!!.strMeasure9 != null) binding.measure9Tv.visibility = View.VISIBLE
//
//        binding.measure10Tv.text = meal?.strMeasure10 ?: ""
//        if (meal!!.strMeasure10 != null) binding.measure10Tv.visibility = View.VISIBLE
//
//        binding.measure11Tv.text = meal?.strMeasure11 ?: ""
//        if (meal!!.strMeasure11 != null) binding.measure11Tv.visibility = View.VISIBLE
//
//        binding.measure12Tv.text = meal?.strMeasure12 ?: ""
//        if (meal!!.strMeasure12 != null) binding.measure12Tv.visibility = View.VISIBLE
//
//        binding.measure13Tv.text = meal?.strMeasure13 ?: ""
//        if (meal!!.strMeasure13 != null) binding.measure13Tv.visibility = View.VISIBLE
//
//        binding.measure14Tv.text = meal?.strMeasure14 ?: ""
//        if (meal!!.strMeasure14 != null) binding.measure14Tv.visibility = View.VISIBLE
//
//        binding.measure15Tv.text = meal?.strMeasure15 ?: ""
//        if (meal!!.strMeasure15 != null) binding.measure15Tv.visibility = View.VISIBLE
//
//
//        binding.measure16Tv.text = meal?.strMeasure16 ?: ""
//        if (meal!!.strMeasure16 != null) binding.measure16Tv.visibility = View.VISIBLE
//
//        binding.measure17Tv.text = meal?.strMeasure17 ?: ""
//        if (meal!!.strMeasure17 != null) binding.measure17Tv.visibility = View.VISIBLE
//
//        binding.measure18Tv.text = meal?.strMeasure18 ?: ""
//        if (meal!!.strMeasure18 != null) binding.measure18Tv.visibility = View.VISIBLE
//
//        binding.measure19Tv.text = meal?.strMeasure19 ?: ""
//        if (meal!!.strMeasure19 != null) binding.measure19Tv.visibility = View.VISIBLE
//
//        binding.measure20Tv.text = meal?.strMeasure20 ?: ""
//        if (meal!!.strMeasure20 != null) binding.measure20Tv.visibility = View.VISIBLE


    }
}