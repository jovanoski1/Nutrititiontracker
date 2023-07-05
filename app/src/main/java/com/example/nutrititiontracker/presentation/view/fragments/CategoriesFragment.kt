package com.example.nutrititiontracker.presentation.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nutrititiontracker.R
import com.example.nutrititiontracker.data.models.CategoriesResponse
import com.example.nutrititiontracker.databinding.FragmentCategoriesBinding
import com.example.nutrititiontracker.presentation.contract.CategoriesContract
import com.example.nutrititiontracker.presentation.view.recycler.adapter.CategoryAdapter
import com.example.nutrititiontracker.presentation.view.states.CategoriesState
import com.example.nutrititiontracker.presentation.viewmodel.CategoriesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    private val categoriesViewModel: CategoriesContract.ViewModel by viewModel<CategoriesViewModel>()


    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CategoryAdapter


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

        initUi()
        initObservers()


    }

    private fun initUi(){
        binding.listRv.layoutManager = LinearLayoutManager(context)
        adapter = CategoryAdapter()
        binding.listRv.adapter = adapter

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
                    adapter.submitList(it.categories)
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

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}