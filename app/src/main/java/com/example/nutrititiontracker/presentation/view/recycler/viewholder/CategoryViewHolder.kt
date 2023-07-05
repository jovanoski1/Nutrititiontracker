package com.example.nutrititiontracker.presentation.view.recycler.viewholder

import android.view.View.OnClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.nutrititiontracker.data.models.CategoriesResponse
import com.example.nutrititiontracker.data.models.CategoryResponse
import com.example.nutrititiontracker.databinding.CategoryListItemBinding
import com.squareup.picasso.Picasso

class CategoryViewHolder(private val itemBinding: CategoryListItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(category: CategoryResponse){
        Picasso.get().load(category.strCategoryThumb).into(itemBinding.categoryIv)
        itemBinding.categoryNameTv.text = category.strCategory
    }
}