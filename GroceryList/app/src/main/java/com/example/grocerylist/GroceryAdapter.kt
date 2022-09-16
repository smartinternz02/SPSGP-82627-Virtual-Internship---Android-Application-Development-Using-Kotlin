package com.example.grocerylist


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ImageButton

import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

class GroceryAdapter(
    var List: List<GroceryItems>,
    private val groceryItemClickInterface: GroceryItemClickInterface
) : RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder>() {
   private lateinit var unit:String
    inner class GroceryViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val itemName: TextView = ItemView.findViewById(R.id.ItemName)
        val itemQuantity: TextView = ItemView.findViewById(R.id.ItemCount)
        val itemRate: TextView = ItemView.findViewById(R.id.ItemRate)
        val itemCost: TextView = ItemView.findViewById(R.id.Cost)
        val delete: ImageButton = ItemView.findViewById(R.id.deleteBtn)
//        val unit:TextView=ItemView.findViewById(R.id.ItemUnit)


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.grocery_recyclerview, parent, false)
        return GroceryViewHolder(view)
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
         holder.itemName.text=List[position].ItemName
        unit=List[position].ItemUnit
        holder.itemQuantity.text=List[position].ItemQuantity.toString()+" $unit "
       // holder.unit.text=List[position].ItemUnit.toString()
        holder.itemRate.text="₹"+List[position].ItemPrice.toString()
        val itemCost:Double=List[position].ItemPrice*List[position].ItemQuantity
        holder.itemCost.text= "₹ $itemCost"
        holder.delete.setOnClickListener{
            groceryItemClickInterface.onItemClick(List[position])
        }
    }

    override fun getItemCount(): Int {
        return List.size
    }
}

interface GroceryItemClickInterface {
    fun onItemClick(groceryItems: GroceryItems)
}