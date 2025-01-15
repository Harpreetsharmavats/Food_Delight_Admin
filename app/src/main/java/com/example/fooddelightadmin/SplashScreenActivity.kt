package com.example.fooddelightadmin

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fooddelightadmin.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    private val binding: ActivitySplashScreenBinding by lazy {
        ActivitySplashScreenBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
Handler(Looper.getMainLooper()).postDelayed({
    val intent = Intent(this,SigninActivity::class.java)
    startActivity(intent)
    finish()
},2000)



    }
}