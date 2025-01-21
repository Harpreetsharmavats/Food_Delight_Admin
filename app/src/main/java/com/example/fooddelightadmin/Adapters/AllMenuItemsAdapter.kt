package com.example.fooddelightadmin.Adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fooddelightadmin.Models.AllMenu

import com.example.fooddelightadmin.databinding.AllmenuitemsBinding

class AllMenuItemsAdapter(
    private val context: Context,
    private val menuList: ArrayList<AllMenu>
) : RecyclerView.Adapter<AllMenuItemsAdapter.AllMenuViewHolder>() {
    private val menuQuantity = IntArray(menuList.size) { 1 }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllMenuViewHolder {
        return AllMenuViewHolder(
            AllmenuitemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: AllMenuViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = menuList.size

    inner class AllMenuViewHolder(private val binding: AllmenuitemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {

            binding.apply {
                val quantity = menuQuantity[position]
                val menuItem =menuList[position]
                val uriString = menuItem.foodImage
                val uri = Uri.parse(uriString)
                menufooditems.text = menuItem.foodName
                menuprice.text = menuItem.foodPrice
                Glide.with(context).load(uri).into(menufoodimage)
                menuquantity.text = quantity.toString()
                plusbtn.setOnClickListener {
                    Increment(position)
                }
                minusbtn.setOnClickListener {
                    Decrement(position)
                }
                deleteitem.setOnClickListener {
                    val itemPosition = adapterPosition
                    if (itemPosition != RecyclerView.NO_POSITION) {
                        DeleteItem(position)
                    }
                }
            }

        }

        fun DeleteItem(position: Int) {
            menuList.removeAt(adapterPosition)

            notifyItemRemoved(adapterPosition)
            notifyItemRangeChanged(adapterPosition,menuList.size)

        }

        fun Decrement(position: Int) {
            if (menuQuantity[position] > 1) {
                menuQuantity[position]--
                binding.menuquantity.text = menuList[position].toString()


            }
        }

        fun Increment(position: Int) {
            if (menuQuantity[position] < 100){
                menuQuantity[position]++
                binding.menuquantity.text = menuQuantity[position].toString()
            }

        }

    }

}