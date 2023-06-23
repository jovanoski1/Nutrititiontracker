package com.example.nutrititiontracker.presentation.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nutrititiontracker.R
import com.example.nutrititiontracker.data.local.models.UserEntity
import com.example.nutrititiontracker.presentation.view.contract.MainContract
import com.example.nutrititiontracker.presentation.view.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    //private val mainViewModel: MainContract.ViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //mainViewModel.insertUser(UserEntity(1,"miha","miha123"))
    }
}