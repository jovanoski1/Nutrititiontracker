package com.example.nutrititiontracker.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.nutrititiontracker.data.models.MealEntity
import com.example.nutrititiontracker.databinding.MyMealListItemBinding
import com.example.nutrititiontracker.databinding.MyMealListItemSelectionBinding
import com.example.nutrititiontracker.presentation.view.recycler.diff.MyMealDiffCallback
import com.example.nutrititiontracker.presentation.view.recycler.listeners.MealPlanMealClickListener
import com.example.nutrititiontracker.presentation.view.recycler.listeners.MyMealClickListener
import com.example.nutrititiontracker.presentation.view.recycler.viewholder.MyMealPlanViewHolder
import com.example.nutrititiontracker.presentation.view.recycler.viewholder.MyMealViewHolder

class MyMealPlanAdapter(private val clickListener: MealPlanMealClickListener): ListAdapter<MealEntity, MyMealPlanViewHolder>(MyMealDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMealPlanViewHolder {
        val itemBinding = MyMealListItemSelectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyMealPlanViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MyMealPlanViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}