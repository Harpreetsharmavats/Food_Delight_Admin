package com.example.fooddelightadmin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelightadmin.Models.UserModel
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
    private lateinit var userId: String

    private var isEnable by Delegates.notNull<Boolean>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        userId = auth.currentUser?.uid?:""
        databaseReference = database.reference.child("adminUser").child(userId)

        binding.backbtn.setOnClickListener {
            finish()
        }

        binding.nameofrestaurent.isEnabled = false
        binding.name.isEnabled = false
        binding.address.isEnabled = false
        binding.email.isEnabled = false
        binding.phone.isEnabled = false
        binding.password.isEnabled = false
        binding.savedetails.isEnabled = false

        isEnable = false
        binding.clicktochangedetails.setOnClickListener {
            isEnable = !isEnable
            binding.nameofrestaurent.isEnabled = isEnable
            binding.name.isEnabled = isEnable
            binding.address.isEnabled = isEnable
            binding.email.isEnabled = isEnable
            binding.phone.isEnabled = isEnable
            binding.password.isEnabled = isEnable
            binding.savedetails.isEnabled = isEnable

            if (isEnable) {
                binding.nameofrestaurent.requestFocus()

            }

        }

        isEnable = true
        binding.savedetails.setOnClickListener {
            updateUserData()
            isEnable = !isEnable
            binding.nameofrestaurent.isEnabled = isEnable
            binding.name.isEnabled = isEnable
            binding.address.isEnabled = isEnable
            binding.email.isEnabled = isEnable
            binding.phone.isEnabled = isEnable
            binding.password.isEnabled = isEnable
            binding.savedetails.isEnabled = isEnable

        }
        retrieveUserData()

    }

    private fun updateUserData() {
        val restaurant = binding.nameofrestaurent.text.toString()
        val name = binding.name.text.toString()
        val address = binding.address.text.toString()
        val email = binding.email.text.toString()
        val phone = binding.phone.text.toString()
        val password = binding.password.text.toString()
        val userData = UserModel(restaurant,name,address,email, phone, password)
        databaseReference.setValue(userData).addOnSuccessListener {
            Toast.makeText(this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show()
            auth.currentUser?.updateEmail(email)
            auth.currentUser?.updatePassword(password)
        }.addOnFailureListener {
            Toast.makeText(this, "Profile Updating Failed", Toast.LENGTH_SHORT).show()

        }
    }

    private fun retrieveUserData() {
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    val restaurant = snapshot.child("nameOfRestaurant").getValue()
                    val userName = snapshot.child("name").getValue()
                    val address = snapshot.child("address").getValue()
                    val email = snapshot.child("email").getValue()
                    val phone = snapshot.child("phone").getValue()
                    val password = snapshot.child("password").getValue()
                    setInfo(restaurant,userName,address,email,phone,password)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    private fun setInfo(
        restaurant: Any?,
        userName: Any?,
        address: Any?,
        email: Any?,
        phone: Any?,
        password: Any?
    ) {
        binding.nameofrestaurent.setText(restaurant.toString())
        binding.name.setText(userName.toString())
        binding.address.setText(address.toString())
        binding.email.setText(email.toString())
        binding.phone.setText(phone.toString())
        binding.password.setText(password.toString())
    }


}