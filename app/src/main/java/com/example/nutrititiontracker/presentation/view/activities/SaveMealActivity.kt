package com.example.nutrititiontracker.presentation.view.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.nutrititiontracker.R
import com.example.nutrititiontracker.data.models.MealResponse
import com.example.nutrititiontracker.data.models.MealType
import com.example.nutrititiontracker.databinding.ActivitySaveMealBinding
import com.squareup.picasso.Picasso

class SaveMealActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySaveMealBinding
    private lateinit var mealToSave:MealResponse


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySaveMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras!=null){
            mealToSave = extras.getSerializable("mealToSave") as MealResponse
        }

        initUi()
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