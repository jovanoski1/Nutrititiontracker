package com.example.nutrititiontracker.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.nutrititiontracker.data.models.MealEntity
import com.example.nutrititiontracker.databinding.MyMealListItemBinding
import com.example.nutrititiontracker.presentation.view.recycler.diff.MyMealDiffCallback
import com.example.nutrititiontracker.presentation.view.recycler.listeners.MyMealClickListener
import com.example.nutrititiontracker.presentation.view.recycler.viewholder.MyMealViewHolder

class MyMealAdapter(private val clickListener: MyMealClickListener): ListAdapter<MealEntity, MyMealViewHolder>(MyMealDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMealViewHolder {
        val itemBinding = MyMealListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyMealViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MyMealViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}