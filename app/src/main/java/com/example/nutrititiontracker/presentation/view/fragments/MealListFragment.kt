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
import com.example.nutrititiontracker.data.models.MealResponse
import com.example.nutrititiontracker.databinding.FragmentMealListBinding
import com.example.nutrititiontracker.presentation.contract.MealsContract
import com.example.nutrititiontracker.presentation.view.recycler.adapter.MealAdapter
import com.example.nutrititiontracker.presentation.view.states.MealsState
import com.example.nutrititiontracker.presentation.viewmodel.MealsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MealListFragment : Fragment() {
    private val mealsViewModel: MealsContract.ViewModel by sharedViewModel<MealsViewModel>()

    private lateinit var mealAdapter: MealAdapter
    private var meals:MutableList<MealResponse> = mutableListOf()
    private var maxPageCnt: Int = 0
    private var currentPage: Int = 1

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
        mealAdapter = MealAdapter()
        binding.listRv.adapter = mealAdapter
    }

    private fun initObservers(){
        mealsViewModel.mealState.observe(this, Observer {
            when(it){
                is MealsState.Success ->{
                    binding.loadingPbMl.isVisible = false
//                    mealAdapter.submitList(it.meals)
                    meals.addAll(it.meals)
                    mealAdapter.submitList(meals.subList(0,10))
                    maxPageCnt = it.meals.size/10 + 1
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
    }
}