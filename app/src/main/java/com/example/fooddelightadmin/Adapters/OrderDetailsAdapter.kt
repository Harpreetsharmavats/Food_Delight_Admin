package com.example.fooddelightadmin.Adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fooddelightadmin.databinding.OrderdetailscardBinding

class OrderDetailsAdapter(private val context: Context,
                          private val foodName: MutableList<String>,
                          private val foodImage: MutableList<String>,
                          private val foodQuantity: MutableList<Int>,
                          private val foodPrice: MutableList<String>,
    ): RecyclerView.Adapter<OrderDetailsAdapter.OrderDetailsViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderDetailsAdapter.OrderDetailsViewHolder {
       return OrderDetailsViewHolder(OrderdetailscardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(
        holder: OrderDetailsAdapter.OrderDetailsViewHolder,
        position: Int
    ) {
       holder.bind(position)
    }

    override fun getItemCount(): Int = foodName.size

    inner class OrderDetailsViewHolder(private val binding: OrderdetailscardBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                name.text = foodName[position]
                val uriString= foodImage[position]
                val uri = Uri.parse(uriString)
                Glide.with(context).load(uri).into(foodimage)
                quantity.text = foodQuantity[position].toString()
                price.text = foodPrice[position]

            }

        }

    }
}