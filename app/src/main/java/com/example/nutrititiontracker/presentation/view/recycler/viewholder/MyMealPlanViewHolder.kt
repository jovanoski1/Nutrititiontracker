package com.example.nutrititiontracker.presentation.view.recycler.viewholder

import android.R
import android.graphics.BitmapFactory
import androidx.recyclerview.widget.RecyclerView
import com.example.nutrititiontracker.data.models.MealEntity
import com.example.nutrititiontracker.databinding.MyMealListItemBinding
import com.example.nutrititiontracker.databinding.MyMealListItemSelectionBinding
import com.example.nutrititiontracker.presentation.view.recycler.listeners.MealPlanMealClickListener
import com.example.nutrititiontracker.presentation.view.recycler.listeners.MyMealClickListener
import com.squareup.picasso.Picasso
import java.io.File


class MyMealPlanViewHolder(private val itemBinding: MyMealListItemSelectionBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(meal: MealEntity, clickListener: MealPlanMealClickListener){
        if (meal.image!!.contains("https")) {
            Picasso.get().load(meal.image).into(itemBinding.categoryIv)
        }
        else {
            val decodedByteArray = android.util.Base64.decode(meal.image, android.util.Base64.DEFAULT)
            val decodedBitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.size)
            itemBinding.categoryIv.setImageBitmap(decodedBitmap)
        }
        itemBinding.categoryNameTv.text = meal.name
        itemBinding.root.setOnClickListener{
            clickListener.onMyMealClick(meal)
        }
    }
}