package com.example.nutrititiontracker.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.nutrititiontracker.data.models.CategoryResponse
import com.example.nutrititiontracker.data.models.MealResponse
import com.example.nutrititiontracker.databinding.CategoryListItemBinding
import com.example.nutrititiontracker.databinding.MealListItemBinding
import com.example.nutrititiontracker.presentation.view.recycler.listeners.CategoryClickListener
import com.squareup.picasso.Picasso

class MealViewHolder(private val itemBinding: MealListItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(meal: MealResponse){
        Picasso.get().load(meal.strMealThumb).into(itemBinding.categoryIv)
        itemBinding.categoryNameTv.text = meal.strMeal
    }
}