package com.example.fooddelightadmin.Adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddelightadmin.databinding.OutfordelivercardBinding

class DeliveryStatusAdapter(private val customername:ArrayList<String>,private val moneystatus:ArrayList<String>) : RecyclerView.Adapter<DeliveryStatusAdapter.DeliveryStatusViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryStatusViewHolder {
        return DeliveryStatusViewHolder(OutfordelivercardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int =customername.size

    override fun onBindViewHolder(holder: DeliveryStatusViewHolder, position: Int) {

        holder.bind(position)
    }
    inner class DeliveryStatusViewHolder(private val binding: OutfordelivercardBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                name.text = customername[position]
                statusmoney.text = moneystatus[position]
                val colorMap = mapOf("Received" to Color.GREEN,"NotReceived" to Color.RED,"Pending" to Color.GRAY)
                statusmoney.setTextColor(colorMap[moneystatus[position]]?:Color.BLACK)
                deliverystatus.backgroundTintList = ColorStateList.valueOf(colorMap[moneystatus[position]]?:Color.BLACK)

            }
        }


    }
}