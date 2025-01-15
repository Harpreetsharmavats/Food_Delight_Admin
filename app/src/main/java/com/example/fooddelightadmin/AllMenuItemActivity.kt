package com.example.fooddelightadmin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelightadmin.databinding.ActivityAllMenuItemBinding

class AllMenuItemActivity : AppCompatActivity() {
    private val binding:ActivityAllMenuItemBinding by lazy {
        ActivityAllMenuItemBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}