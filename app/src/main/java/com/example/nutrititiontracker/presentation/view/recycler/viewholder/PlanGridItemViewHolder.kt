package com.example.nutrititiontracker.presentation.view.recycler.viewholder

import android.graphics.BitmapFactory
import androidx.recyclerview.widget.RecyclerView
import com.example.nutrititiontracker.data.models.MealEntity
import com.example.nutrititiontracker.databinding.MealPlanGridItemBinding
import com.example.nutrititiontracker.presentation.view.recycler.listeners.GridPlanClickListener
import com.example.nutrititiontracker.presentation.view.recycler.listeners.MealClickListener
import com.example.nutrititiontracker.presentation.view.recycler.listeners.MyMealClickListener
import com.squareup.picasso.Picasso


class PlanGridItemViewHolder(private val itemBinding: MealPlanGridItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(meal: MealEntity, clickListener: GridPlanClickListener){
        if (meal.image==null){

        }
        else if (meal.image!!.contains("https")) {
            Picasso.get().load(meal.image).into(itemBinding.iv)
        }
        else {
            val decodedByteArray = android.util.Base64.decode(meal.image, android.util.Base64.DEFAULT)
            val decodedBitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.size)
            itemBinding.iv.setImageBitmap(decodedBitmap)
        }

        itemBinding.root.setOnClickListener{
            clickListener.onItemClick(meal, position)
        }
    }
}