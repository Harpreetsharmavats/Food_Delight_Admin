package com.example.fooddelightadmin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelightadmin.databinding.ActivityAdminProfileBinding
import kotlin.properties.Delegates

class AdminProfileActivity : AppCompatActivity() {
    private val binding: ActivityAdminProfileBinding by lazy {
        ActivityAdminProfileBinding.inflate(layoutInflater)
    }
    private var isEnable by Delegates.notNull<Boolean>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
binding.backbtn.setOnClickListener {
    finish()
}
        binding.name.isEnabled=false
        binding.address.isEnabled=false
        binding.email.isEnabled=false
        binding.phone.isEnabled=false
        binding.password.isEnabled=false

        isEnable = false
        binding.clicktochangedetails.setOnClickListener {
            isEnable =! isEnable
            binding.name.isEnabled=isEnable
            binding.address.isEnabled=isEnable
            binding.email.isEnabled=isEnable
            binding.phone.isEnabled=isEnable
            binding.password.isEnabled=isEnable
            if (isEnable) {
            binding.name.requestFocus()

            }

        }

            isEnable = true
            binding.savedetails.setOnClickListener {
                isEnable =! isEnable
                binding.name.isEnabled=isEnable
                binding.address.isEnabled=isEnable
                binding.email.isEnabled=isEnable
                binding.phone.isEnabled=isEnable
                binding.password.isEnabled=isEnable

        }

    }
}