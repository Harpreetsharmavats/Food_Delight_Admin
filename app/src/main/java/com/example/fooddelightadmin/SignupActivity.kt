package com.example.fooddelightadmin

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelightadmin.databinding.ActivitySignupBinding


class SignupActivity : AppCompatActivity() {
    private val binding: ActivitySignupBinding by lazy {
        ActivitySignupBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val locationlist = arrayOf("Delhi", "New Delhi","Gurgaon","Sonipat","Rohtak","Charkhi Dadri")
        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,locationlist)
        val autoCompleteTextView = binding.listofcity
        autoCompleteTextView.setAdapter(adapter)

        binding.alreadyhaveanacc.setOnClickListener {
            val intent = Intent(this,SigninActivity::class.java)
            startActivity(intent)
        }
       binding.createaccount.setOnClickListener {
           val intent = Intent(this,MainActivity::class.java)
           startActivity(intent)
       }
    }
}