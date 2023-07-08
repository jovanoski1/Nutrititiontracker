package com.example.nutrititiontracker.presentation.view.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nutrititiontracker.data.models.CategoryResponse
import com.example.nutrititiontracker.data.models.MealResponse
import com.example.nutrititiontracker.databinding.FragmentMealListBinding
import com.example.nutrititiontracker.presentation.contract.MealsContract
import com.example.nutrititiontracker.presentation.view.recycler.adapter.MealAdapter
import com.example.nutrititiontracker.presentation.view.recycler.listeners.MealClickListener
import com.example.nutrititiontracker.presentation.view.states.MealsState
import com.example.nutrititiontracker.presentation.viewmodel.MealsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.min

class MealListFragment : Fragment() {
    private val mealsViewModel: MealsContract.ViewModel by viewModel<MealsViewModel>()

    private lateinit var mealAdapter: MealAdapter
    private var meals:List<MealResponse> = listOf()
    private var maxPageCnt: Int = 0
    private var currentPage: Int = 1
    private var mainIngredientMealNameToggle: Boolean = false

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
        initListeners()


        val bundle = arguments
        if (bundle !=null){
            val category = bundle.getSerializable("categorySelected") as CategoryResponse
            mealsViewModel.fetchAllMealsByCategory(category.strCategory)
        }
    }

    private fun initUi(){
        binding.listRv.layoutManager = LinearLayoutManager(context)
        mealAdapter = MealAdapter(object : MealClickListener{
            override fun onItemClick(mealResponse: MealResponse) {
            }
        })
        binding.listRv.adapter = mealAdapter
    }

    private fun initObservers(){
        mealsViewModel.mealState.observe(this, Observer {
            when(it){
                is MealsState.Success ->{
//                    val lock = Object()
//                    synchronized(lock){
                        meals = it.meals.toList()
                        mealAdapter.submitList(meals.subList(0, min(10, meals.size)))
                        currentPage = 1
                        binding.pageCntMealListTv.text = currentPage.toString()
//                    }
                    maxPageCnt = it.meals.size/10 + 1
                    binding.loadingPbMl.isVisible = false
                    binding.listRv.isVisible = true
                }
                is MealsState.Loading ->{
                    binding.loadingPbMl.isVisible = true
                    binding.listRv.isVisible = false
                }
                else -> {
                    println("CEKAJ Meals $it")
//                    meals.clear()
//                    mealAdapter.submitList(meals)
                    binding.loadingPbMl.isVisible = false
                    binding.listRv.isVisible = true
                }
            }
        })
    }

    private fun initListeners(){
        binding.previousBtn.setOnClickListener{
            if (currentPage > 1){
                currentPage --
                mealAdapter.submitList(meals.subList((currentPage-1)*10,currentPage*10))
                binding.pageCntMealListTv.text = currentPage.toString()
            }
        }
        binding.nextBtn.setOnClickListener{
            if (currentPage < maxPageCnt){
                currentPage++
                mealAdapter.submitList(meals.subList((currentPage-1)*10,
                    meals.size.coerceAtMost(currentPage * 10)
                ))
                binding.pageCntMealListTv.text = currentPage.toString()
            }
        }

        binding.searchMealListPageEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (mainIngredientMealNameToggle){
                    mealsViewModel.fetchAllMealsByMainIngredient(p0.toString())
                }
                else{
                    mealsViewModel.fetchMealByName(p0.toString())
//                    if (p0.toString().length == 1)
//                        mealsViewModel.fetchAllMealsByFirstLetter(p0.toString()[0])
//                    else {
//                        mealsViewModel.fetchMealByName(p0.toString())
//                    }
                }
            }

        })

        binding.mealNameMealListPageBtn.setOnClickListener {
            mainIngredientMealNameToggle = false
        }
        binding.mainIngredientMealListPageBtn.setOnClickListener{
            mainIngredientMealNameToggle = true
        }
    }
}