package com.example.grocerylist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GroceryViewModel(private val repo: GroceryItemRepo) : ViewModel() {
    fun insert(items: GroceryItems) = GlobalScope.launch {
        repo.insert(items)
    }
    fun delete(items:GroceryItems)= GlobalScope.launch {
        repo.delete(items)
    }
    fun getAllGroceryItems()=repo.getAllItems()

}