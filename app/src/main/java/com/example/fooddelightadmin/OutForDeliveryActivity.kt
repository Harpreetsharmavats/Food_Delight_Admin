package com.example.fooddelightadmin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddelightadmin.Adapters.DeliveryStatusAdapter
import com.example.fooddelightadmin.databinding.ActivityOutForDeliveryBinding

class OutForDeliveryActivity : AppCompatActivity() {
    private val binding : ActivityOutForDeliveryBinding by lazy {
        ActivityOutForDeliveryBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.outfordeliverbackbtn.setOnClickListener {
            finish()
        }
        val customerName = arrayListOf("Gullu","gagan","Aman","Gajra")
        val moneyStatus = arrayListOf("Notreceived","received","received","pending")
        val adapter = DeliveryStatusAdapter(customerName,moneyStatus)
        binding.outfordeliverrv.layoutManager = LinearLayoutManager(this)
        binding.outfordeliverrv.adapter = adapter

    }
}