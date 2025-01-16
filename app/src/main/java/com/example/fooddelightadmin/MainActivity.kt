package com.example.fooddelightadmin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelightadmin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        binding.cardView2.setOnClickListener {
            val intent = Intent(this,AllMenuItemActivity::class.java)
            startActivity(intent)
        }
        binding.cardView5.setOnClickListener {
            val intent = Intent(this,AdminProfileActivity::class.java)
            startActivity(intent)
        }
        binding.cardView4.setOnClickListener {
            val intent =Intent(this,CreateNewUserActivity::class.java)
            startActivity(intent)
        }

    }
}