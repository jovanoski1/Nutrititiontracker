package com.example.nutrititiontracker.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.nutrititiontracker.data.models.MealEntity
import com.example.nutrititiontracker.databinding.MealPlanGridItemBinding
import com.example.nutrititiontracker.presentation.view.recycler.diff.PlanGridDiffCallback
import com.example.nutrititiontracker.presentation.view.recycler.listeners.MealClickListener
import com.example.nutrititiontracker.presentation.view.recycler.listeners.MyMealClickListener
import com.example.nutrititiontracker.presentation.view.recycler.viewholder.PlanGridItemViewHolder

class PlanGridAdapter(private val clickListener: MyMealClickListener): ListAdapter<MealEntity, PlanGridItemViewHolder>(PlanGridDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanGridItemViewHolder {
        val itemBinding = MealPlanGridItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlanGridItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PlanGridItemViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}