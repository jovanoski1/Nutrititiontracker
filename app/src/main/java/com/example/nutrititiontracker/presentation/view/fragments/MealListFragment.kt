package com.example.nutrititiontracker.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nutrititiontracker.data.models.CategoryResponse
import com.example.nutrititiontracker.databinding.FragmentMealListBinding
import com.example.nutrititiontracker.presentation.contract.MealsContract
import com.example.nutrititiontracker.presentation.view.recycler.adapter.MealAdapter
import com.example.nutrititiontracker.presentation.view.states.MealsState
import com.example.nutrititiontracker.presentation.viewmodel.MealsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MealListFragment : Fragment() {
    private val mealsViewModel: MealsContract.ViewModel by sharedViewModel<MealsViewModel>()

    private lateinit var mealAdapter: MealAdapter


    private var _binding: FragmentMealListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMealListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initObservers()


        val bundle = arguments
        if (bundle !=null){
            val category = bundle.getSerializable("categorySelected") as CategoryResponse
            mealsViewModel.fetchAllMealsByCategory(category.strCategory)
        }
    }

    private fun initUi(){
        binding.listRv.layoutManager = LinearLayoutManager(context)
        mealAdapter = MealAdapter()
        binding.listRv.adapter = mealAdapter
    }

    private fun initObservers(){
        mealsViewModel.mealState.observe(this, Observer {
            when(it){
                is MealsState.Success ->{
                    binding.loadingPbMl.isVisible = false
                    mealAdapter.submitList(it.meals)
//                    println( it.meals.toString())
                    binding.listRv.isVisible = true
                }
                is MealsState.Loading ->{
                    binding.loadingPbMl.isVisible = true
                    binding.listRv.isVisible = false
                }
                else -> {
                    println("CEKAJ Meals $it")
                    binding.loadingPbMl.isVisible = false
                    binding.listRv.isVisible = true
                }
            }
        })
    }
}