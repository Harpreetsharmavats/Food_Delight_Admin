package com.example.fooddelightadmin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelightadmin.databinding.ActivityOrdersDetailsBinding

class OrdersDetailsActivity : AppCompatActivity() {
    private val binding : ActivityOrdersDetailsBinding by lazy {
        ActivityOrdersDetailsBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.imageButton.setOnClickListener {
            finish()
        }

    }
}