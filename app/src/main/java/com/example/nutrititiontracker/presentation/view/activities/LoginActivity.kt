package com.example.nutrititiontracker.presentation.view.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.nutrititiontracker.R
import com.example.nutrititiontracker.databinding.ActivityLoginBinding
import com.example.nutrititiontracker.presentation.view.contract.MainContract
import com.example.nutrititiontracker.presentation.view.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val mainViewModel: MainContract.ViewModel by viewModel<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()
    }

    private fun initListener(){

        binding.loginBtn.setOnClickListener {
            val username: String = binding.editTextUsername.text.toString()
            val password: String = binding.editTextPassword.text.toString()
            mainViewModel.getUser(username, password)
            mainViewModel.loggedUser.observe(this, Observer {
                if (it != null){
                    val sharedPreferences: SharedPreferences by inject()
                    val editor = sharedPreferences.edit()
                    editor.putLong("userId", it.id)
                    editor.putString("username", it.username)
                    editor.apply()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            })
        }
    }
}