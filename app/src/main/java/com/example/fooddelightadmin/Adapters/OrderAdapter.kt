package com.example.fooddelightadmin.Adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fooddelightadmin.databinding.PendingordercardBinding

class OrderAdapter(
    private val context: Context,
    private val customerName: MutableList<String>,
    private val orderPrice: MutableList<String>,
    private val image: MutableList<String>,

    private val itemClicked : OnItemClicked
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {
interface OnItemClicked{
    fun onItemClickListener(position: Int)
    fun onItemAcceptClickListener(position: Int)
    fun onItemDispatchListener(position: Int)
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(
            PendingordercardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = customerName.size

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class OrderViewHolder(private val binding: PendingordercardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var isAccepted = false
        fun bind(position: Int) {
            binding.apply {
                name.text = customerName[position]
                totalamount.text = orderPrice[position]
                val uriString = image[position]
                val uri = Uri.parse(uriString)
                Glide.with(context).load(uri).into(foodimage)
                acceptdispatchbtn.apply {
                    if (!isAccepted) {
                        text = "Accept"
                    } else {
                        text = "Dispatch"
                    }
                    setOnClickListener {
                        if (!isAccepted) {
                            text = "Dispatch"
                            isAccepted = true
                            showtoast("Order is Accepted")
                            itemClicked.onItemAcceptClickListener(position)
                        } else {
                            customerName.removeAt(adapterPosition)
                            notifyItemRemoved(adapterPosition)
                            showtoast("Order is dispatched")
                            itemClicked.onItemDispatchListener(position)

                        }
                    }
                }
                itemView.setOnClickListener {
                    itemClicked.onItemClickListener(position)
                }
            }

        }

        private fun showtoast(message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

    }
}