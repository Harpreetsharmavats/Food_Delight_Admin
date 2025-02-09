package com.example.fooddelightadmin


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelightadmin.Models.OrderDetails
import com.example.fooddelightadmin.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()


        setContentView(binding.root)
        binding.allmenuitems.setOnClickListener {
            val intent = Intent(this,AllMenuItemActivity::class.java)
            startActivity(intent)
        }
        binding.profile.setOnClickListener {
            val intent = Intent(this,AdminProfileActivity::class.java)
            startActivity(intent)
        }
        binding.createnewuser.setOnClickListener {
            val intent =Intent(this,CreateNewUserActivity::class.java)
            startActivity(intent)
        }
        binding.addmenu.setOnClickListener {
            val intent = Intent(this,AddItemsActivity::class.java)
            startActivity(intent)
        }
        binding.orderdispatched.setOnClickListener {
            val intent = Intent(this,OutForDeliveryActivity::class.java)
            startActivity(intent)
        }
        binding.pendingorders.setOnClickListener {
            val intent = Intent(this,PendingOrderActivity::class.java)
            startActivity(intent)
        }
pendingOrders()
        completedOrders()
        wholeTimeEarning()
        binding.logout.setOnClickListener {

        }

    }

    private fun wholeTimeEarning() {
        val listOfTotalPay = mutableListOf<Int>()
        databaseReference = FirebaseDatabase.getInstance().getReference("CompletedOrder")
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(orderSnapshot in snapshot.children){
                    val orderEarnings = orderSnapshot.getValue(OrderDetails::class.java)
                    orderEarnings?.totalPrice?.replace("$", "")?.toIntOrNull()?.let {
                        listOfTotalPay.add(it)
                    }
                }
                binding.wholeearnings.text = listOfTotalPay.sum().toString() + "$"
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun completedOrders() {
        val completedOrderRef = database.reference.child("CompletedOrder")
        var completedCount = 0
        completedOrderRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                completedCount = snapshot.childrenCount.toInt()
                binding.completenumbers.text = completedCount.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun pendingOrders() {
        val pendingOrdersRef = database.reference.child("OrderDetails")
        var ordersCount = 0
        pendingOrdersRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                ordersCount = snapshot.childrenCount.toInt()
                binding.numberofpendingorders.text = ordersCount.toString()

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}