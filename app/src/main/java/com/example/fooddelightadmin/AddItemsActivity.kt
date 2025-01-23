package com.example.fooddelightadmin

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddelightadmin.Models.AllMenu
import com.example.fooddelightadmin.databinding.ActivityAddItemsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

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

        binding.selectimage.setOnClickListener {
            pickImage.launch("image/*")
        }

        binding.additembtn.setOnClickListener {
            foodName = binding.itemname.text.toString().trim()
            foodPrice= binding.itemprice.text.toString().trim()
            foodDescription = binding.shortdescriptio.text.toString().trim()
            foodIngredient = binding.ingredients.text.toString().trim()
            if (!(foodName.isBlank() || foodPrice.isBlank() || foodDescription.isBlank() || foodIngredient.isBlank())){

                uploadData()

            }else{
                Toast.makeText(this,"Fill all the details",Toast.LENGTH_SHORT).show()
            }
        }
        binding.additembackbtn.setOnClickListener {
            finish()
        }

    }



    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            binding.selectedimage.setImageURI(uri)
            foodImageUri= uri
        }

    }


    private fun uploadData() {
        val menuRef = database.getReference("menu")

        val newItemKey = menuRef.push().key


        if (foodImageUri == null){
            Toast.makeText(this,"Please select image",Toast.LENGTH_SHORT).show()

        }else{
            val storageRef = FirebaseStorage.getInstance().reference
            val imageRef = storageRef.child("menu_image/${UUID.randomUUID()}.jpg")

             imageRef.putFile(foodImageUri!!).continueWithTask {
                imageRef.downloadUrl.addOnSuccessListener {
                        downloadUrl ->
                    val newItems = AllMenu(
                        foodName = foodName,
                        foodPrice = foodPrice,
                        foodDescription = foodDescription,
                        foodIngredient = foodIngredient,
                        foodImage = downloadUrl.toString()
                    )
                    newItemKey?.let {

                        menuRef.child(newItemKey).setValue(newItems).addOnSuccessListener {
                            Toast.makeText(this,"Data uploaded successfully",Toast.LENGTH_SHORT).show()
                        }
                            .addOnFailureListener {
                                Toast.makeText(this,"Data not uploaded",Toast.LENGTH_SHORT).show()
                                Log.d("DatabaseError","Error: ${it.message}")
                            }
                    }
                }
            }
                .addOnFailureListener {
                    Toast.makeText(this,"Image not uploaded",Toast.LENGTH_SHORT).show()
                    Log.d("DatabaseError","Error: ${it.message}")
                }
        }
        finish()
    }

}