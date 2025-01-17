package com.example.fooddelightadmin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddelightadmin.Adapters.OrderAdapter
import com.example.fooddelightadmin.databinding.ActivityPendingOrderBinding

class PendingOrderActivity : AppCompatActivity() {
    private val binding:ActivityPendingOrderBinding by lazy {
        ActivityPendingOrderBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.pendingorderbackbtn.setOnClickListener {
            finish()
        }

        val name = arrayListOf("Pappu","Sambhu","Sunita")
        val quantity = arrayListOf("5","3","10")
        val foodimage = arrayListOf(R.drawable.menu3,R.drawable.menu1,R.drawable.menu4)
        val adapter = OrderAdapter(name,quantity,foodimage,this)
        binding.pendingorderrv.layoutManager = LinearLayoutManager(this)
        binding.pendingorderrv.adapter = adapter

    }
}