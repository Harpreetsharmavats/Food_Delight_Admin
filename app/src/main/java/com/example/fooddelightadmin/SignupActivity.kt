package com.example.fooddelightadmin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelightadmin.Models.UserModel
import com.example.fooddelightadmin.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class SignupActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var address: String
    private lateinit var name: String
    private lateinit var nameofrestaurant: String
    private lateinit var email: String
    private lateinit var phone: String
    private lateinit var password: String
    private lateinit var database: DatabaseReference
    private val binding: ActivitySignupBinding by lazy {
        ActivitySignupBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //initialize Firebase auth
        auth = Firebase.auth
        //initialize of databse
        database = Firebase.database.reference


       binding.createaccount.setOnClickListener {
           nameofrestaurant = binding.nameofrestaurent.text.toString().trim()
           name = binding.name.text.toString().trim()
           address = binding.address.text.toString().trim()
           email = binding.email.text.toString().trim()
           phone = binding.phone.text.toString().trim()
           password = binding.pass.text.toString().trim()



           if (email.isBlank() || password.isBlank() || nameofrestaurant.isBlank() || name.isBlank() || phone.isBlank() || address.isBlank()){
               Toast.makeText(this,"Please fill the all details",Toast.LENGTH_SHORT).show()
           }else{
               createAccount(email,password)
           }


       }
        binding.alreadyhaveanacc.setOnClickListener {
            val intent = Intent(this,SigninActivity::class.java)
            startActivity(intent)
        }

    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if (task.isSuccessful){
                Toast.makeText(this,"Account is created",Toast.LENGTH_SHORT).show()
                saveUserdetails()
                val intent = Intent(this,SigninActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,"Account creation failed",Toast.LENGTH_SHORT).show()
                Log.d("Account","createAccount: Failure",task.exception)
            }

        }
    }

    private fun saveUserdetails() {

        address = binding.address.text.toString().trim()
        name = binding.name.text.toString().trim()
        nameofrestaurant = binding.nameofrestaurent.text.toString().trim()
        email = binding.email.text.toString().trim()
        phone = binding.phone.text.toString().trim()
        password = binding.pass.text.toString().trim()
        val user = UserModel(nameofrestaurant,name,address, email, phone, password)
        val userId:String = FirebaseAuth.getInstance().currentUser!!.uid
        database.child("adminUser").child(userId).setValue(user)
    }
}