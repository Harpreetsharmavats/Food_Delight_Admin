package com.example.fooddelightadmin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelightadmin.Models.OrderDetails
import com.example.fooddelightadmin.databinding.ActivityOutForDeliveryBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class OutForDeliveryActivity : AppCompatActivity() {
    private val binding : ActivityOutForDeliveryBinding by lazy {
        ActivityOutForDeliveryBinding.inflate(layoutInflater)
    }
    private lateinit var database: FirebaseDatabase
    private val  listOfCompletedOrderItems:ArrayList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.outfordeliverbackbtn.setOnClickListener {
            finish()
        }
        //retrieve and display completed order
        retrieveCompleteOrderDetails()

    }

    private fun retrieveCompleteOrderDetails() {
        database =FirebaseDatabase.getInstance()
        val completeOrderRef = database.reference.child("CompletedOrder").orderByChild("currentTime")
        completeOrderRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                listOfCompletedOrderItems.clear()
                for (orderSnapshot in snapshot.children){
                   val completeOrder = orderSnapshot.getValue(OrderDetails::class.java)
                    completeOrder?.let { listOfCompletedOrderItems.add(it.toString())
                    }

                }
                listOfCompletedOrderItems.reverse()
                //setAdapter(listOfCompletedOrderItems)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

   /* private fun setAdapter(listOfCompletedOrderItems: ArrayList<String>) {
        val customerName = mutableListOf<String>()
        val deliveryStatus = mutableListOf<Boolean>()

        for (order in this.listOfCompletedOrderItems){
            order.userName?.let {  }
        }
        val adapter = DeliveryStatusAdapter()
        binding.outfordeliverrv.layoutManager = LinearLayoutManager(this)
        binding.outfordeliverrv.adapter = adapter

    }*/
}