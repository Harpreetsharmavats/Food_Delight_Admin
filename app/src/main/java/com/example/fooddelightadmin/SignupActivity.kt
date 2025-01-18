package com.example.fooddelightadmin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
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
    private val binding: ActivitySignupBinding by lazy {
        ActivitySignupBinding.inflate(layoutInflater)
    }
private lateinit var auth: FirebaseAuth
private lateinit var name: String
private lateinit var nameofrestaurant: String
private lateinit var email: String
private lateinit var password: String
private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //initialize Firebase auth
        auth = Firebase.auth
        //initialize of databse
        database = Firebase.database.reference


       binding.createaccount.setOnClickListener {
           email = binding.email.editText.toString().trim()
           password = binding.pass.editText.toString().trim()
           name = binding.name.editText.toString().trim()
           nameofrestaurant = binding.nameofrestaurent.editText.toString().trim()

           if (email.isBlank() || password.isBlank() || nameofrestaurant.isBlank() || name.isBlank()){
               Toast.makeText(this,"Please fill the all details",Toast.LENGTH_SHORT).show()
           }else{
               createaccount(email,password)
           }


       }
        binding.alreadyhaveanacc.setOnClickListener {
            val intent = Intent(this,SigninActivity::class.java)
            startActivity(intent)
        }
        val locationlist = arrayOf("Delhi", "New Delhi","Gurgaon","Sonipat","Rohtak","Charkhi Dadri")
        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,locationlist)
        val autoCompleteTextView = binding.listofcity
        autoCompleteTextView.setAdapter(adapter)
    }

    private fun createaccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if (task.isSuccessful){
                Toast.makeText(this,"Account is created",Toast.LENGTH_SHORT).show()
                saveUserdetails()
                val intent = Intent(this,SigninActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this,"Account creation failed",Toast.LENGTH_SHORT).show()
                Log.d("Account","createaccount: Failure",task.exception)
            }

        }
    }

    private fun saveUserdetails() {

        name = binding.name.editText.toString().trim()
        nameofrestaurant = binding.nameofrestaurent.editText.toString().trim()
        email = binding.email.editText.toString().trim()
        password = binding.pass.editText.toString().trim()
        val user = UserModel(email,password,name,nameofrestaurant)
        val userId:String = FirebaseAuth.getInstance().currentUser!!.uid
        database.child("user").child(userId).setValue(user)
    }
}