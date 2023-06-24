package com.example.nutrititiontracker.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.nutrititiontracker.databinding.ActivityMainBinding
import com.example.nutrititiontracker.presentation.contract.MainContract
import com.example.nutrititiontracker.presentation.view.states.CategoriesState
import com.example.nutrititiontracker.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainContract.ViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding.root)

        mainViewModel.fetchAllCategories()

        mainViewModel.categoriesState.observe(this, Observer {
            when(it){
                is CategoriesState.Success ->{
                    binding.loadingPb.isVisible = false
                    println(it.categories)
                }
                is CategoriesState.Loading ->{
                    binding.loadingPb.isVisible = true
                }
                else -> {
                    println("CEKAJ")
                    binding.loadingPb.isVisible = false

                }
            }
        })
    }
}