package com.example.nutrititiontracker.presentation.view.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.nutrititiontracker.R
import com.example.nutrititiontracker.data.models.AreaResponse
import com.example.nutrititiontracker.data.models.CategoryResponse
import com.example.nutrititiontracker.data.models.IngredientResponse
import com.example.nutrititiontracker.data.models.MealResponse
import com.example.nutrititiontracker.databinding.FragmentCategoriesBinding
import com.example.nutrititiontracker.databinding.FragmentFilterBinding
import com.example.nutrititiontracker.presentation.contract.CategoriesContract
import com.example.nutrititiontracker.presentation.contract.MealsContract
import com.example.nutrititiontracker.presentation.view.recycler.adapter.*
import com.example.nutrititiontracker.presentation.view.states.AreasState
import com.example.nutrititiontracker.presentation.view.states.CategoriesState
import com.example.nutrititiontracker.presentation.view.states.IngredientsState
import com.example.nutrititiontracker.presentation.view.states.MealsState
import com.example.nutrititiontracker.presentation.viewmodel.CategoriesViewModel
import com.example.nutrititiontracker.presentation.viewmodel.MealsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.math.min


class FilterFragment : Fragment() {
    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!

    private val categoriesViewModel: CategoriesContract.ViewModel by viewModel<CategoriesViewModel>()
    private val mealsViewModel: MealsContract.ViewModel by sharedViewModel<MealsViewModel>()

    private var meals:MutableList<MealResponse> = mutableListOf()
    private var maxPageCnt: Int = 0
    private var currentPage: Int = 1

    private var areas:MutableList<AreaResponse> = mutableListOf()
    private var ingredients:MutableList<IngredientResponse> = mutableListOf()
    private var categories:MutableList<CategoryResponse> = mutableListOf()

    private lateinit var categorySpinnerAdapter: CategorySpinnerAdapter
    private lateinit var areaSpinnerAdapter: AreaSpinnerAdapter
    private lateinit var ingredientSpinnerAdapter: IngredientSpinnerAdapter
    private lateinit var mealsAdapter: MealAdapter



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

        binding.listRv.layoutManager = LinearLayoutManager(context)
        mealsAdapter = MealAdapter()
        binding.listRv.adapter = mealsAdapter

        initObservers()
        initListeners()

        categoriesViewModel.fetchAllAreas()
        categoriesViewModel.fetchAllIngredients()
        categoriesViewModel.fetchAllCategories()
    }

    private fun initListeners(){

        binding.searchMealListPageEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                    mealsViewModel.fetchMealByName(p0.toString())
                }
        })

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val adapter = binding.spinner.adapter
                if (adapter is CategorySpinnerAdapter) {
                    val selectedItem = adapter.getItem(position) as CategoryResponse
                    mealsViewModel.fetchAllMealsByCategory(selectedItem.strCategory)

                } else if (adapter is AreaSpinnerAdapter) {
                    val selectedItem = adapter.getItem(position) as AreaResponse
                    mealsViewModel.fetchAllMealsByArea(selectedItem.strArea)
                }
                else if (adapter is IngredientSpinnerAdapter) {
                    val selectedItem = adapter.getItem(position) as IngredientResponse
                    mealsViewModel.fetchAllMealsByMainIngredient(selectedItem.strIngredient)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

        binding.areaBtn.setOnClickListener{
            binding.spinner.adapter = areaSpinnerAdapter
        }
        binding.categoryBtn.setOnClickListener{
            binding.spinner.adapter = categorySpinnerAdapter
        }
        binding.ingredientBtn.setOnClickListener{
            binding.spinner.adapter = ingredientSpinnerAdapter
        }

        binding.previousBtn.setOnClickListener{
            if (currentPage > 1){
                currentPage --

                mealsAdapter.submitList(meals.subList((currentPage - 1) * 10, currentPage * 10))
                binding.pageCntMealListTv.text = currentPage.toString()
            }
        }
        binding.nextBtn.setOnClickListener{
            if (currentPage < maxPageCnt){
                currentPage++
                mealsAdapter.submitList(meals.subList((currentPage - 1) * 10, meals.size.coerceAtMost(currentPage * 10)))
                binding.pageCntMealListTv.text = currentPage.toString()
            }
        }

    }
    private fun initObservers(){
        mealsViewModel.mealState.observe(this, Observer {
            when(it){
                is MealsState.Success ->{
                    binding.loadingPbMl.isVisible = false
                    binding.listRv.isVisible = true
//                    mealsAdapter.submitList(it.meals)

                    meals = it.meals.toMutableList()
                    mealsAdapter.submitList(meals.subList(0, min(10, meals.size)))
                    currentPage = 1
                    binding.pageCntMealListTv.text = currentPage.toString()

                    maxPageCnt = it.meals.size/10 + 1
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