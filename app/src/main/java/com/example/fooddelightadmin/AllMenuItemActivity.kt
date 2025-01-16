package com.example.fooddelightadmin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddelightadmin.Adapters.AllMenuItemsAdapter
import com.example.fooddelightadmin.databinding.ActivityAllMenuItemBinding

class AllMenuItemActivity : AppCompatActivity() {
    private val binding:ActivityAllMenuItemBinding by lazy {
        ActivityAllMenuItemBinding.inflate(layoutInflater)
    }
    private lateinit var adapter: AllMenuItemsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.backbtn.setOnClickListener {
            finish()
        }
        val menuFoodName = listOf("Ice cream", "Soup", "Pasta", "Roll")
        val menuPrice = listOf("$1", "$4", "$7", "$5")
        val menuFoodImage =
            listOf(R.drawable.menu3, R.drawable.menu4, R.drawable.menu5, R.drawable.menu6)
        adapter = AllMenuItemsAdapter(
            ArrayList(menuFoodName), ArrayList(menuPrice),
            ArrayList(menuFoodImage))
        binding.allmenurv.layoutManager = LinearLayoutManager(this)
        binding.allmenurv.adapter =adapter
    }
}