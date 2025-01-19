package com.example.fooddelightadmin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelightadmin.databinding.ActivitySigninBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SigninActivity : AppCompatActivity() {
    private lateinit var email:String
    private lateinit var password:String
    private lateinit var auth: FirebaseAuth
    private lateinit var database:DatabaseReference
    private val binding: ActivitySigninBinding by lazy {
        ActivitySigninBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = Firebase.auth

        database = Firebase.database.reference

        binding.loginbtn.setOnClickListener {
            email = binding.email.text.toString().trim()
            password = binding.pass.text.toString().trim()
            if (email.isBlank() || password.isBlank()){
                Toast.makeText(this,"Please fill the details",Toast.LENGTH_SHORT).show()
            }else{
                signIn(email,password)
            }
        }

binding.donothaveacc.setOnClickListener {
    val intent = Intent(this,SignupActivity::class.java)
    startActivity(intent)
}

    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if (task.isSuccessful){
                Toast.makeText(this,"Signed in successfully",Toast.LENGTH_SHORT).show()
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }else {
                Toast.makeText(this,"Authentication failed",Toast.LENGTH_SHORT).show()
                Log.d("Account","signIn : Authentication failed",task.exception)
            }
            }
        }
    }
