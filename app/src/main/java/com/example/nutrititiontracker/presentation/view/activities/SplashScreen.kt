package com.example.nutrititiontracker.presentation.view.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.example.nutrititiontracker.R
import org.koin.android.ext.android.inject

@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val sharedPreferences: SharedPreferences by inject()
        val id:Long = sharedPreferences.getLong("userId", -1L)
//        println("User with id: $id logged" )
        val intent:Intent = if (id == -1L) Intent(this, LoginActivity::class.java)
        else Intent(this, MainActivity::class.java)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(intent)
            finish()
        }, 2000) // 3000 is the delayed time in milliseconds.
    }
}