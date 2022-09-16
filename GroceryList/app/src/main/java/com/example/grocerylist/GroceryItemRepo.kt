package com.example.grocerylist

class GroceryItemRepo(private val db: GroceryItemDataBase) {
suspend fun insert(items: GroceryItems)=db.groceryDao().insert(items)
 suspend fun delete(items:GroceryItems)=db.groceryDao().delete(items)
 fun getAllItems()=db.groceryDao().getAllGroceryItems()
}