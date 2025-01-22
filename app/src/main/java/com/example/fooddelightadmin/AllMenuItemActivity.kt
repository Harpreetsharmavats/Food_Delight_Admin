package com.example.fooddelightadmin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddelightadmin.Adapters.AllMenuItemsAdapter
import com.example.fooddelightadmin.Models.AllMenu
import com.example.fooddelightadmin.databinding.ActivityAllMenuItemBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AllMenuItemActivity : AppCompatActivity() {
    private lateinit var database: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private var menuItems: ArrayList<AllMenu> = ArrayList()
    private val binding:ActivityAllMenuItemBinding by lazy {
        ActivityAllMenuItemBinding.inflate(layoutInflater)
    }
    private lateinit var adapter: AllMenuItemsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        databaseReference= FirebaseDatabase.getInstance().reference
        retrieveMenuItem()
        binding.backbtn.setOnClickListener {
            finish()
        }


    }

    fun setAdapter() {
        val adapter = AllMenuItemsAdapter(this@AllMenuItemActivity,menuItems,databaseReference)
        binding.allmenurv.layoutManager = LinearLayoutManager(this)
        binding.allmenurv.adapter =adapter
    }

    private fun retrieveMenuItem() {
        database = FirebaseDatabase.getInstance()
        val foodRef: DatabaseReference = database.reference.child("menu")

        foodRef.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot){
                menuItems.clear()
                for (foodSnapshot in snapshot.children){
                    val menuItem = foodSnapshot.getValue(AllMenu::class.java)
                    menuItem?.let {
                        menuItems.add(it)
                    }
                }
                setAdapter()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("DatabaseError","Error: ${error.message}")
            }
        })
    }
}