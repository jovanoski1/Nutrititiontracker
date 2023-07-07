package com.example.nutrititiontracker.presentation.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.nutrititiontracker.R
import com.example.nutrititiontracker.data.models.AreaResponse
import com.example.nutrititiontracker.data.models.CategoryResponse
import com.example.nutrititiontracker.data.models.IngredientResponse
import com.example.nutrititiontracker.data.models.MealResponse
import com.example.nutrititiontracker.databinding.FragmentCategoriesBinding
import com.example.nutrititiontracker.databinding.FragmentFilterBinding
import com.example.nutrititiontracker.presentation.contract.CategoriesContract
import com.example.nutrititiontracker.presentation.view.recycler.adapter.AreaSpinnerAdapter
import com.example.nutrititiontracker.presentation.view.recycler.adapter.CategoryAdapter
import com.example.nutrititiontracker.presentation.view.recycler.adapter.CategorySpinnerAdapter
import com.example.nutrititiontracker.presentation.view.recycler.adapter.IngredientSpinnerAdapter
import com.example.nutrititiontracker.presentation.view.states.AreasState
import com.example.nutrititiontracker.presentation.view.states.CategoriesState
import com.example.nutrititiontracker.presentation.view.states.IngredientsState
import com.example.nutrititiontracker.presentation.viewmodel.CategoriesViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class FilterFragment : Fragment() {
    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!

    private val categoriesViewModel: CategoriesContract.ViewModel by sharedViewModel<CategoriesViewModel>()

    private var areas:MutableList<AreaResponse> = mutableListOf()
    private var ingredients:MutableList<IngredientResponse> = mutableListOf()
    private var categories:MutableList<CategoryResponse> = mutableListOf()

    private lateinit var categorySpinnerAdapter: CategorySpinnerAdapter
    private lateinit var areaSpinnerAdapter: AreaSpinnerAdapter
    private lateinit var ingredientSpinnerAdapter: IngredientSpinnerAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initListeners()

        categoriesViewModel.fetchAllAreas()
        categoriesViewModel.fetchAllIngredients()
        categoriesViewModel.fetchAllCategories()
    }

    private fun initListeners(){
        binding.areaBtn.setOnClickListener{
            binding.spinner.adapter = areaSpinnerAdapter
        }
        binding.categoryBtn.setOnClickListener{
            binding.spinner.adapter = categorySpinnerAdapter
        }
        binding.ingredientBtn.setOnClickListener{
            binding.spinner.adapter = ingredientSpinnerAdapter
        }
    }
    private fun initObservers(){
        categoriesViewModel.categoriesState.observe(this, Observer {
            when(it){
                is CategoriesState.Success ->{
                    binding.loadingPbMl.isVisible = false
                    categories = it.categories.toMutableList()
                    categorySpinnerAdapter = context?.let { it1 -> CategorySpinnerAdapter(it1, R.layout.spinner_item, categories,"strCategory") }!!

                }
                is CategoriesState.Loading ->{
                    binding.loadingPbMl.isVisible = true
                }
                else -> {
                    println("CEKAJ Categories $it")
                    binding.loadingPbMl.isVisible = false

                }
            }
        })
        categoriesViewModel.areasState.observe(this, Observer {
            when(it){
                is AreasState.Success ->{
                    binding.loadingPbMl.isVisible = false
                    areas = it.areas.toMutableList()
                    areaSpinnerAdapter = context?.let { it1 -> AreaSpinnerAdapter(it1, R.layout.spinner_item, areas,"strArea") }!!
                }
                is AreasState.Loading ->{
                    binding.loadingPbMl.isVisible = true
                }
                else -> {
                    println("CEKAJ Areas $it")
                    binding.loadingPbMl.isVisible = false
                }
            }
        })
        categoriesViewModel.ingredientsState.observe(this, Observer {
            when(it){
                is IngredientsState.Success ->{
                    binding.loadingPbMl.isVisible = false
                    ingredients = it.ingredients.toMutableList()
                    ingredientSpinnerAdapter = context?.let { it1 -> IngredientSpinnerAdapter(it1, R.layout.spinner_item, ingredients,"strIngredient") }!!

                }
                is IngredientsState.Loading ->{
                    binding.loadingPbMl.isVisible = true
                }
                else -> {
                    println("CEKAJ Ingredients $it")
                    binding.loadingPbMl.isVisible = false
                }
            }
        })
    }
}