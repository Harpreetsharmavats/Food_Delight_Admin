package com.example.fooddelightadmin.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.fooddelightadmin.databinding.AllmenuitemsBinding

class AllMenuItemsAdapter(
    private val menuitems: ArrayList<String>,
    private val price: ArrayList<String>,
    private val menuimage: ArrayList<Int>
) : RecyclerView.Adapter<AllMenuItemsAdapter.AllMenuViewHolder>() {
    private val menuquantity = IntArray(menuitems.size) { 1 }
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
        holder.bind(
            menuitems[position],
            price[position],
            menuimage[position],
            menuquantity[position]
        )
    }

    override fun getItemCount(): Int = menuitems.size

    inner class AllMenuViewHolder(private val binding: AllmenuitemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(items: String, price: String, image: Int, quantity: Int) {

            binding.apply {
                menufooditems.text = items
                menuprice.text = price
                menufoodimage.setImageResource(image)
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
            menuitems.removeAt(adapterPosition)

            notifyItemRemoved(adapterPosition)
            notifyItemRangeChanged(adapterPosition,menuquantity.size)

        }

        fun Decrement(position: Int) {
            if (menuquantity[position] > 1) {
                menuquantity[position]--
                binding.menuquantity.text = menuquantity[position].toString()


            }
        }

        fun Increment(position: Int) {
            if (menuquantity[position] < 100){
                menuquantity[position]++
                binding.menuquantity.text = menuquantity[position].toString()
            }

        }

    }

}