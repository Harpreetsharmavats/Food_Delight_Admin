package com.example.fooddelightadmin

import android.os.Bundle
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelightadmin.databinding.ActivityAddItemsBinding

class AddItemsActivity : AppCompatActivity() {
    private val binding: ActivityAddItemsBinding by lazy {
        ActivityAddItemsBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.additembackbtn.setOnClickListener {
            finish()
        }
binding.selectimage.setOnClickListener {
    pickimage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
}
    }
    val pickimage = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){uri ->
        if (uri != null){
            binding.selectedimage.setImageURI(uri)
        }

    }
}