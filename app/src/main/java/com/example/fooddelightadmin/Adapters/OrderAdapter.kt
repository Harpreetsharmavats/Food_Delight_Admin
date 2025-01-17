package com.example.fooddelightadmin.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddelightadmin.databinding.PendingordercardBinding

class OrderAdapter(private val customername:ArrayList<String>,private val orderquantity:ArrayList<String>,private val image:ArrayList<Int>,private val context: Context): RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(PendingordercardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int =customername.size

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class OrderViewHolder (private val binding: PendingordercardBinding) : RecyclerView.ViewHolder(binding.root){
        private var isAccepted = false
        fun bind(position: Int) {
            binding.apply {
                name.text = customername[position]
                quantity.text = orderquantity[position]
                foodimage.setImageResource(image[position])

                acceptdispatchbtn.apply {
                    if (!isAccepted){
                        text = "Accept"
                    }else{
                        text = "Dispatch"
                    }
                    setOnClickListener {
                        if (!isAccepted){
                            text = "Dispatch"
                            isAccepted = true
                            showtoast("Order is Accepted")
                        }else{
                            customername.removeAt(adapterPosition)
                            notifyItemRemoved(adapterPosition)
                            showtoast("Order is dispatched")

                        }
                    }
                }
            }

        }
        private fun showtoast(message: String){
            Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
        }

    }
}