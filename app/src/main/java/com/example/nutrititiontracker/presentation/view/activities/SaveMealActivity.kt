package com.example.nutrititiontracker.presentation.view.activities

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.nutrititiontracker.R
import com.example.nutrititiontracker.data.models.MealEntity
import com.example.nutrititiontracker.data.models.MealResponse
import com.example.nutrititiontracker.data.models.MealType
import com.example.nutrititiontracker.databinding.ActivitySaveMealBinding
import com.example.nutrititiontracker.presentation.contract.MealsContract
import com.example.nutrititiontracker.presentation.viewmodel.MealsViewModel
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SaveMealActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySaveMealBinding
    private lateinit var mealToSave:MealResponse
    private val mealsViewModel: MealsContract.ViewModel by viewModel<MealsViewModel>()
    private val sharedPreferences: SharedPreferences by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySaveMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras!=null){
            mealToSave = extras.getSerializable("mealToSave") as MealResponse
        }

        val id:Long = sharedPreferences.getLong("userId", -1L)

        initUi()
        initListeners()
        initObservers()
    }

    private fun initObservers() {
        mealsViewModel.mealStateForUser.observe(this, Observer {
            for( x in it){
                println(x.name+" "+x.mealType)
            }
        })
    }

    private fun initListeners() {
        binding.saveMealBtn.setOnClickListener{
            val mealType:MealType = binding.mealSpinner.selectedItem as MealType
            val id:Long = sharedPreferences.getLong("userId", -1L)

            mealsViewModel.insertMeal(MealEntity(
                category = mealToSave.strCategory,
                image = mealToSave.strMealThumb,
                instructions = mealToSave.strInstructions,
                name = mealToSave.strMeal,
                mealType = mealType,
                strIngredient1 = mealToSave.strIngredient1,
                strIngredient2 = mealToSave.strIngredient2,
                strIngredient3 = mealToSave.strIngredient3,
                strIngredient4 = mealToSave.strIngredient4,
                strIngredient5 = mealToSave.strIngredient5,
                strIngredient6 = mealToSave.strIngredient6,
                strIngredient7 = mealToSave.strIngredient7,
                strIngredient8 = mealToSave.strIngredient8,
                strIngredient9 = mealToSave.strIngredient9,
                strIngredient10 = mealToSave.strIngredient10,
                strIngredient11 = mealToSave.strIngredient11,
                strIngredient12 = mealToSave.strIngredient12,
                strIngredient13 = mealToSave.strIngredient13,
                strIngredient14 = mealToSave.strIngredient14,
                strIngredient15 = mealToSave.strIngredient15,
                strIngredient16 = mealToSave.strIngredient16,
                strIngredient17 = mealToSave.strIngredient17,
                strIngredient18 = mealToSave.strIngredient18,
                strIngredient19 = mealToSave.strIngredient19,
                strIngredient20 = mealToSave.strIngredient20,
                strMeasure1 = mealToSave.strMeasure1,
                strMeasure2 = mealToSave.strMeasure2,
                strMeasure3 = mealToSave.strMeasure3,
                strMeasure4 = mealToSave.strMeasure4,
                strMeasure5 = mealToSave.strMeasure5,
                strMeasure6 = mealToSave.strMeasure6,
                strMeasure7 = mealToSave.strMeasure7,
                strMeasure8 = mealToSave.strMeasure8,
                strMeasure9 = mealToSave.strMeasure9,
                strMeasure10 = mealToSave.strMeasure10,
                strMeasure11 = mealToSave.strMeasure11,
                strMeasure12 = mealToSave.strMeasure12,
                strMeasure13 = mealToSave.strMeasure13,
                strMeasure14 = mealToSave.strMeasure14,
                strMeasure15 = mealToSave.strMeasure15,
                strMeasure16 = mealToSave.strMeasure16,
                strMeasure17 = mealToSave.strMeasure17,
                strMeasure18 = mealToSave.strMeasure18,
                strMeasure19 = mealToSave.strMeasure19,
                strMeasure20 = mealToSave.strMeasure20,
                urlToVideo = mealToSave.strYoutube,
                userId = id
            ))

            Toast.makeText(this, "Successfully saved meal", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun initUi() {
        val mealTypeValues:Array<MealType> = MealType.values()
        val adapter = ArrayAdapter<MealType>(this, android.R.layout.simple_spinner_item, mealTypeValues)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.mealSpinner.adapter = adapter
        Picasso.get().load(mealToSave.strMealThumb).into(binding.mealImageIv)
        binding.mealNameTv.text = mealToSave.strMeal
    }
}