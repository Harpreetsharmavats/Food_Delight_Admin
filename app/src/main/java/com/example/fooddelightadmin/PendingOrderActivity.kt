package com.example.fooddelightadmin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddelightadmin.Adapters.OrderAdapter
import com.example.fooddelightadmin.Models.OrderDetails
import com.example.fooddelightadmin.databinding.ActivityPendingOrderBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PendingOrderActivity : AppCompatActivity(),OrderAdapter.OnItemClicked {
    private lateinit var auth: FirebaseAuth
    private lateinit var database : FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private var listOfName: MutableList<String> = mutableListOf()
    private var listOfTotalPrice: MutableList<String> = mutableListOf()
    private var listOfIamgesofFirstFood: MutableList<String> = mutableListOf()
    private var listOfOrderItems: MutableList<OrderDetails> = mutableListOf()
    private val binding:ActivityPendingOrderBinding by lazy {
        ActivityPendingOrderBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance()
        databaseReference =database.reference.child("OrderDetails")

        getOrdersDetails()

        binding.pendingorderbackbtn.setOnClickListener {
            finish()
        }



    }

    private fun getOrdersDetails() {
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (foodSnapshot in snapshot.children){
                    val orderDetails =foodSnapshot.getValue(OrderDetails::class.java)
                    orderDetails?.let { listOfOrderItems.add(it) }
                }
                addDataToRV()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }

    private fun addDataToRV() {
        for (orderItem in listOfOrderItems){
            orderItem.userName?.let { listOfName.add(it) }
            orderItem.totalPrice?.let { listOfTotalPrice.add(it) }
            orderItem.foodImage?.filterNot { it.isEmpty() }?.forEach {
                listOfIamgesofFirstFood.add(it)
            }
        }
        setAdapter()
    }

    private fun setAdapter() {
        binding.pendingorderrv.layoutManager = LinearLayoutManager(this)
        val adapter = OrderAdapter(this,listOfName,listOfTotalPrice,listOfIamgesofFirstFood,this)
        binding.pendingorderrv.adapter = adapter
    }


    override fun onItemClickListener(position: Int) {
        val intent = Intent(this,OrdersDetailsActivity::class.java)
        val userOrderDetails = listOfOrderItems[position]
        intent.putExtra("UserOrderDetails",userOrderDetails)
        startActivity(intent)
    }
}