package com.example.nutrititiontracker.presentation.view.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nutrititiontracker.R
import com.example.nutrititiontracker.data.models.CategoryResponse
import com.example.nutrititiontracker.databinding.FragmentCategoriesBinding
import com.example.nutrititiontracker.presentation.contract.CategoriesContract
import com.example.nutrititiontracker.presentation.contract.MealsContract
import com.example.nutrititiontracker.presentation.view.dialogs.CategoryDetailsDialog
import com.example.nutrititiontracker.presentation.view.recycler.adapter.CategoryAdapter
import com.example.nutrititiontracker.presentation.view.recycler.adapter.MealAdapter
import com.example.nutrititiontracker.presentation.view.recycler.listeners.CategoryClickListener
import com.example.nutrititiontracker.presentation.view.states.CategoriesState
import com.example.nutrititiontracker.presentation.view.states.MealsState
import com.example.nutrititiontracker.presentation.viewmodel.CategoriesViewModel
import com.example.nutrititiontracker.presentation.viewmodel.MealsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class CategoriesFragment : Fragment() {

    private val categoriesViewModel: CategoriesContract.ViewModel by sharedViewModel<CategoriesViewModel>()
    private val mealsViewModel: MealsContract.ViewModel by sharedViewModel<MealsViewModel>()


    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var mealAdapter: MealAdapter
    private var mainIngredientMealNameToggle: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoriesViewModel.fetchAllCategories()
        //mealsViewModel.fetchAllMealsByArea("Canadian")

        initUi()
        initObservers()


    }

    private fun initUi(){
        binding.listRv.layoutManager = LinearLayoutManager(context)
        categoryAdapter = CategoryAdapter(categoryClickListener = object : CategoryClickListener{
            override fun onDetailsClick(categoryResponse: CategoryResponse) {
                println(categoryResponse.strCategory+" kliknuo")
                CategoryDetailsDialog(categoryResponse).show(parentFragmentManager, "Category details dialog")
            }

            override fun onCategoryClick(categoryResponse: CategoryResponse) {
//                println("Kliknuo na sve")
                val transaction = parentFragmentManager.beginTransaction()
                val bundle = Bundle().apply {
                    putSerializable("categorySelected", categoryResponse)
                }
                val fragment = MealListFragment()
                fragment.arguments = bundle
                transaction.replace(R.id.container, fragment)
                transaction.commit()
            }
        })

        mealAdapter = MealAdapter()

        binding.listRv.adapter = categoryAdapter

//        val category1 = CategoriesResponse(1,"prokic","aaa","aaqwe")
//
//        val category2 = CategoriesResponse(2,"miha","aaa","aaqwe")
//
//        val category3 = CategoriesResponse(3,"zare","aaa","aaqwe")


//        adapter.submitList(listOf(category1, category2, category3))
    }

    private fun initObservers(){
        categoriesViewModel.categoriesState.observe(this, Observer {
            when(it){
                is CategoriesState.Success ->{
                    binding.loadingPb.isVisible = false
//                    println(it.categories.size)
                    categoryAdapter.submitList(it.categories)
                }
                is CategoriesState.Loading ->{
                    binding.loadingPb.isVisible = true
                }
                else -> {
                    println("CEKAJ $it")
                    binding.loadingPb.isVisible = false

                }
            }
        })

        mealsViewModel.mealState.observe(this, Observer {
            when(it){
                is MealsState.Success ->{
                    binding.loadingPb.isVisible = false
                    mealAdapter.submitList(it.meals)
//                    println( it.meals.toString())
                    binding.listRv.isVisible = true
                }
                is MealsState.Loading ->{
                    binding.loadingPb.isVisible = true
                    binding.listRv.isVisible = false
                }
                else -> {
                    println("CEKAJ Meals $it")
                    binding.loadingPb.isVisible = false
                    binding.listRv.isVisible = true
                }
            }
        })

        binding.searchMainPageEt.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0.isNullOrEmpty() or p0.isNullOrBlank()){
                    binding.listRv.adapter = categoryAdapter
                    return
                }
                else{
                    binding.listRv.adapter = mealAdapter
                }
                if (mainIngredientMealNameToggle){
                    mealsViewModel.fetchAllMealsByMainIngredient(p0.toString())
                }
                else{
                    if (p0.toString().length == 1)
                        mealsViewModel.fetchAllMealsByFirstLetter(p0.toString()[0])
                    else {
                        var myList = mealAdapter.currentList
//                        when(mealsViewModel.mealState.value){
//                            is MealsState.Success -> {myList = (mealsViewModel.mealState.value as MealsState.Success).meals}
//                        }
                        println(p0.toString())
                        myList = (mealsViewModel.mealState.value as MealsState.Success).meals.filter{ item->
                            item.strMeal.startsWith(p0.toString(), true)
                        }
                        mealAdapter.submitList(myList)
                    }
                }
            }

        })

        binding.mealNameMainPageBtn.setOnClickListener {
            mainIngredientMealNameToggle = false
        }
        binding.mainIngredientMainPageBtn.setOnClickListener{
            mainIngredientMealNameToggle = true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}