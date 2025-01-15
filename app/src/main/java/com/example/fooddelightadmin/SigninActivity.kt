package com.example.fooddelightadmin

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fooddelightadmin.databinding.ActivitySigninBinding

class SigninActivity : AppCompatActivity() {
    private val binding: ActivitySigninBinding by lazy {
        ActivitySigninBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
binding.donothaveacc.setOnClickListener {
    val intent = Intent(this,SignupActivity::class.java)
    startActivity(intent)
}

    }
}