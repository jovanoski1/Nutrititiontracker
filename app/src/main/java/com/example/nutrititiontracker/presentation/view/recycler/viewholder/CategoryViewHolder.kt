package com.example.nutrititiontracker.presentation.view.recycler.viewholder

import android.view.View.OnClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.nutrititiontracker.data.models.CategoriesResponse
import com.example.nutrititiontracker.data.models.CategoryResponse
import com.example.nutrititiontracker.databinding.CategoryListItemBinding
import com.example.nutrititiontracker.presentation.view.recycler.listeners.CategoryClickListener
import com.squareup.picasso.Picasso

class CategoryViewHolder(private val itemBinding: CategoryListItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(category: CategoryResponse, clickListener: CategoryClickListener){
        Picasso.get().load(category.strCategoryThumb).into(itemBinding.categoryIv)
        itemBinding.categoryNameTv.text = category.strCategory

        itemBinding.detailsIv.setOnClickListener {
            clickListener.onDetailsClick(category)
        }

        itemBinding.root.setOnClickListener{
            clickListener.onCategoryClick(category)
        }
    }
}