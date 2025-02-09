package com.example.fooddelightadmin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelightadmin.Models.UserModel
import com.example.fooddelightadmin.databinding.ActivityCreateNewUserBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CreateNewUserActivity : AppCompatActivity() {
    private val binding : ActivityCreateNewUserBinding by lazy {
        ActivityCreateNewUserBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var userId : String
    private lateinit var address: String
    private lateinit var name: String
    private lateinit var nameofrestaurant: String
    private lateinit var email: String
    private lateinit var phone: String
    private lateinit var password: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        userId = auth.currentUser?.uid?:""
        databaseReference = database.reference.child("adminUser").child(userId)
        binding.createnewbackbtn.setOnClickListener {
            finish()
        }
        binding.createnewuserbtn.setOnClickListener {
            nameofrestaurant = binding.nameofrestaurent.text.toString().trim()
           name = binding.name.text.toString().trim()
           address  = binding.address.text.toString().trim()
            email = binding.email.text.toString().trim()
             phone= binding.phone.text.toString().trim()
            password = binding.pass.text.toString().trim()



            if (email.isBlank() || password.isBlank() || nameofrestaurant.isBlank() || name.isBlank() || phone.isBlank() || address.isBlank()){
                Toast.makeText(this,"Please fill the all details", Toast.LENGTH_SHORT).show()
            }else{
                createAccount(email,password)
            }


        }
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task->
            if (task.isSuccessful){
            Toast.makeText(this, "Account Is Created", Toast.LENGTH_SHORT).show()
                saveUserDetails()
            }else{
                Toast.makeText(this, "Account Creation Failed", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun saveUserDetails() {
        /*address = binding.address.text.toString().trim()
        name = binding.name.text.toString().trim()
        nameofrestaurant = binding.nameofrestaurent.text.toString().trim()
        email = binding.email.text.toString().trim()
        phone = binding.phone.text.toString().trim()
        password = binding.pass.text.toString().trim()*/
        val userData = UserModel(nameofrestaurant,name,address, email, phone, password)
        databaseReference.setValue(userData)
    }
}