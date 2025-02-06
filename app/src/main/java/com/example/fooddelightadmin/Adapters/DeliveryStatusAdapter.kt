package com.example.fooddelightadmin.Adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddelightadmin.databinding.OutfordelivercardBinding

class DeliveryStatusAdapter(private val customerName:MutableList<String>,private val moneyStatus:MutableList<Boolean>) : RecyclerView.Adapter<DeliveryStatusAdapter.DeliveryStatusViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryStatusViewHolder {
        return DeliveryStatusViewHolder(OutfordelivercardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int =customerName.size

    override fun onBindViewHolder(holder: DeliveryStatusViewHolder, position: Int) {

        holder.bind(position)
    }
    inner class DeliveryStatusViewHolder(private val binding: OutfordelivercardBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                name.text = customerName[position]
                if (moneyStatus[position] == true){
                    statusmoney.text = "Received"
                }else{
                    statusmoney.text = "Not Received"
                }

                val colorMap = mapOf(true to Color.GREEN,false to Color.RED)
                statusmoney.setTextColor(colorMap[moneyStatus[position]]?:Color.BLACK)
                deliverystatus.backgroundTintList = ColorStateList.valueOf(colorMap[moneyStatus[position]]?:Color.BLACK)

            }
        }


    }
}