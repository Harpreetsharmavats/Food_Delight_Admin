package com.example.fooddelightadmin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelightadmin.databinding.ActivityOrdersDetailsBinding

class OrdersDetailsActivity : AppCompatActivity() {
    private val binding : ActivityOrdersDetailsBinding by lazy {
        ActivityOrdersDetailsBinding.inflate(layoutInflater)
    }

    private var userName :String? = null
    private var address :String? = null
    private var phone :String? = null
    private var total :String? = null
    private var foodName:MutableList<String> = mutableListOf()
    private var foodImage:MutableList<String> = mutableListOf()
    private var foodQuantity:MutableList<String> = mutableListOf()
    private var foodPrice:MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.imageButton.setOnClickListener {
            finish()
        }

    }
}