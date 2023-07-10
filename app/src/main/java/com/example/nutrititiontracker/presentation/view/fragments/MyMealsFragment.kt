package com.example.nutrititiontracker.presentation.view.fragments

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nutrititiontracker.data.models.MealEntity
import com.example.nutrititiontracker.databinding.FragmentMyMealsBinding
import com.example.nutrititiontracker.presentation.contract.MealsContract
import com.example.nutrititiontracker.presentation.view.activities.EditMyMealActivity
import com.example.nutrititiontracker.presentation.view.recycler.adapter.MyMealAdapter
import com.example.nutrititiontracker.presentation.view.recycler.listeners.MyMealClickListener
import com.example.nutrititiontracker.presentation.viewmodel.MealsViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MyMealsFragment : Fragment() {
    private var _binding: FragmentMyMealsBinding? = null
    private val binding get() = _binding!!
    private val mealsViewModel: MealsContract.ViewModel by sharedViewModel<MealsViewModel>()
    private val sharedPreferences: SharedPreferences by inject()
    private lateinit var mealsAdapter: MyMealAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mealsViewModel.getMealsForUser(sharedPreferences.getLong("userId", -1))
        _binding = FragmentMyMealsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.listRv.layoutManager = LinearLayoutManager(context)
        mealsAdapter = MyMealAdapter(object : MyMealClickListener {
            override fun onEditClick(mealEntity: MealEntity) {
                val intent = Intent(context, EditMyMealActivity::class.java)
                intent.putExtra("mealToEdit", mealEntity)
                startActivityForResult(intent, 3352)
            }

            override fun onDeleteClick(mealEntity: MealEntity) {
                Snackbar.make(
                    view,
                    "Confirm deletition of " + mealEntity.name,
                    Snackbar.LENGTH_SHORT
                )
                    .setAction("Confirm", View.OnClickListener {
                        mealsViewModel.deleteMeal(mealEntity)
                    }).show()
            }

        })
        binding.listRv.adapter = mealsAdapter

        initObservers()
    }


    private fun initObservers() {
        mealsViewModel.mealsForUser.observe(this, Observer {
            mealsAdapter.submitList(it)
        })
    }
}