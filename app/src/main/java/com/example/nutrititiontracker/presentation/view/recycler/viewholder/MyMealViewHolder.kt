package com.example.nutrititiontracker.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.nutrititiontracker.data.models.MealEntity
import com.example.nutrititiontracker.databinding.MyMealListItemBinding
import com.example.nutrititiontracker.presentation.view.recycler.listeners.MyMealClickListener
import com.squareup.picasso.Picasso

class MyMealViewHolder(private val itemBinding: MyMealListItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(meal: MealEntity, clickListener: MyMealClickListener){
        Picasso.get().load(meal.image).into(itemBinding.categoryIv)
        itemBinding.categoryNameTv.text = meal.name

        itemBinding.editIv.setOnClickListener{
            clickListener.onEditClick(meal)
        }

        itemBinding.deleteIv.setOnClickListener {
            clickListener.onDeleteClick(meal)
        }
    }
}