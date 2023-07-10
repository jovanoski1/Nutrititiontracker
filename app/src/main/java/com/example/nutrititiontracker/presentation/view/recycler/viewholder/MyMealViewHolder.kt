package com.example.nutrititiontracker.presentation.view.recycler.viewholder

import android.R
import android.graphics.BitmapFactory
import androidx.recyclerview.widget.RecyclerView
import com.example.nutrititiontracker.data.models.MealEntity
import com.example.nutrititiontracker.databinding.MyMealListItemBinding
import com.example.nutrititiontracker.presentation.view.recycler.listeners.MyMealClickListener
import com.squareup.picasso.Picasso
import java.io.File


class MyMealViewHolder(private val itemBinding: MyMealListItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(meal: MealEntity, clickListener: MyMealClickListener){
        if (meal.image!!.contains("https")) {
            Picasso.get().load(meal.image).into(itemBinding.categoryIv)
        }
        else {
            val decodedByteArray = android.util.Base64.decode(meal.image, android.util.Base64.DEFAULT)
            val decodedBitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.size)
            itemBinding.categoryIv.setImageBitmap(decodedBitmap)
        }
        itemBinding.categoryNameTv.text = meal.name

        itemBinding.editIv.setOnClickListener{
            clickListener.onEditClick(meal)
        }

        itemBinding.deleteIv.setOnClickListener {
            clickListener.onDeleteClick(meal)
        }
    }
}