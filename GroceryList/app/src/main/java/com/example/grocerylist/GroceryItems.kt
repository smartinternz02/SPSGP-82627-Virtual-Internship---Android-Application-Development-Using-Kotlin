package com.example.grocerylist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "groceryItems")
data class GroceryItems(

    @ColumnInfo(name = "ItemName") val ItemName:String,
    @ColumnInfo(name = "ItemQuantity") val ItemQuantity:Int,
    @ColumnInfo(name = "ItemPrice") val ItemPrice:Double,
    @ColumnInfo(name = "ItemUnit") val ItemUnit:String,
    @PrimaryKey(autoGenerate = true) val id:Int=0
)




