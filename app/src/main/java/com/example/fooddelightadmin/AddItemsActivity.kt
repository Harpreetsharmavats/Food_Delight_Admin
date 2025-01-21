package com.example.fooddelightadmin

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelightadmin.Models.AllMenu
import com.example.fooddelightadmin.databinding.ActivityAddItemsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class AddItemsActivity : AppCompatActivity() {
    private lateinit var foodName: String
    private lateinit var foodPrice: String
    private var foodImageUri: Uri? = null
    private lateinit var foodDescription: String
    private lateinit var foodIngredient: String
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private val binding: ActivityAddItemsBinding by lazy {
        ActivityAddItemsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth =FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        binding.additembtn.setOnClickListener {
            foodName = binding.itemname.editText?.text.toString().trim()
            foodPrice= binding.itemprice.editText?.text.toString().trim()
            foodDescription = binding.shortdescriptio.text.toString().trim()
            foodIngredient = binding.ingredients.text.toString().trim()
            if (!(foodName.isBlank() || foodPrice.isBlank() || foodDescription.isBlank() || foodIngredient.isBlank())){
                uploadData()
                Toast.makeText(this,"Item added successfully",Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this,"Fill all the details",Toast.LENGTH_SHORT).show()
            }
        }
        binding.additembackbtn.setOnClickListener {
            finish()
        }
        binding.selectimage.setOnClickListener {
            pickImage.launch("image/*")
        }
    }

    private fun uploadData() {
       val menuRef = database.getReference("menu")
        val newItemKey = menuRef.push().key
        if (foodImageUri != null){
            val storageRef = FirebaseStorage.getInstance().reference
            val imageRef= storageRef.child("menu_image/${newItemKey}.jpg")
            val uploadTask = imageRef.putFile(foodImageUri!!)
            uploadTask.addOnSuccessListener {
                imageRef.downloadUrl.addOnSuccessListener {
                    downloadUrl ->
                    val newItems = AllMenu(
                        foodName = foodName,
                        foodPrice = foodPrice,
                        foodDescription = foodDescription,
                        foodIngredient = foodIngredient,
                      foodImage = downloadUrl.toString()
                    )
                    newItemKey?.let{
                       key ->
                        menuRef.child(key).setValue(newItems).addOnSuccessListener {
                            Toast.makeText(this,"Data uploaded successfully",Toast.LENGTH_SHORT).show()
                        }
                            
                            .addOnFailureListener {
                                Toast.makeText(this,"Data not uploaded",Toast.LENGTH_SHORT).show()
                            }
                    }
                }
            }
                .addOnFailureListener {
                Toast.makeText(this,"Image not uploaded",Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this,"Please select image",Toast.LENGTH_SHORT).show()
        }
    }

   private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            binding.selectedimage.setImageURI(uri)
            foodImageUri= uri
        }

    }
}