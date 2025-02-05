package com.example.fooddelightadmin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddelightadmin.Adapters.OrderDetailsAdapter
import com.example.fooddelightadmin.Models.OrderDetails
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
    private var foodQuantity:MutableList<Int> = mutableListOf()
    private var foodPrice:MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.imageButton.setOnClickListener {
            finish()
        }
        getDataFromIntent()


    }

    private fun getDataFromIntent() {
        val recoverOrderDetails = intent.getSerializableExtra("UserOrderDetails") as OrderDetails
        if (recoverOrderDetails != null){
            userName = recoverOrderDetails.userName
            address = recoverOrderDetails.address
            phone = recoverOrderDetails.phoneNumber
            total = recoverOrderDetails.totalPrice
            foodName = recoverOrderDetails.foodName!!
            foodImage = recoverOrderDetails.foodImage!!
            foodQuantity = recoverOrderDetails.foodQuantity!!
            foodPrice = recoverOrderDetails.foodPrice!!
        }
        setUserDetails()
        setAdapter()
    }



    private fun setAdapter() {
        binding.orderdetailrv.layoutManager = LinearLayoutManager(this)
        val adapter = OrderDetailsAdapter(this,foodName,foodImage,foodQuantity,foodPrice)
        binding.orderdetailrv.adapter =adapter
    }



    private fun setUserDetails() {
        binding.name.text = userName
        binding.address.text = address
        binding.phone.text = phone
        binding.total.text = total
    }
}