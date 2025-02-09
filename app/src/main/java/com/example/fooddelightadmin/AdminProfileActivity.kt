package com.example.fooddelightadmin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelightadmin.databinding.ActivityAdminProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.properties.Delegates

class AdminProfileActivity : AppCompatActivity() {
    private val binding: ActivityAdminProfileBinding by lazy {
        ActivityAdminProfileBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    private var isEnable by Delegates.notNull<Boolean>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database.reference.child("adminUser")

        binding.backbtn.setOnClickListener {
            finish()
        }
        binding.name.isEnabled = false
        binding.address.isEnabled = false
        binding.email.isEnabled = false
        binding.phone.isEnabled = false
        binding.password.isEnabled = false

        isEnable = false
        binding.clicktochangedetails.setOnClickListener {
            isEnable = !isEnable
            binding.name.isEnabled = isEnable
            binding.address.isEnabled = isEnable
            binding.email.isEnabled = isEnable
            binding.phone.isEnabled = isEnable
            binding.password.isEnabled = isEnable
            binding.savedetails.isEnabled = isEnable

            if (isEnable) {
                binding.name.requestFocus()

            }

        }

        isEnable = true
        binding.savedetails.setOnClickListener {
            isEnable = !isEnable
            binding.name.isEnabled = isEnable
            binding.address.isEnabled = isEnable
            binding.email.isEnabled = isEnable
            binding.phone.isEnabled = isEnable
            binding.password.isEnabled = isEnable
            binding.savedetails.isEnabled = isEnable

        }
        retrieveUserData()

    }

    private fun retrieveUserData() {
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    val userName = snapshot.child("").getValue()
                    val address = snapshot.child("").getValue()
                    val email = snapshot.child("").getValue()
                    val phone = snapshot.child("").getValue()
                    val password = snapshot.child("").getValue()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}