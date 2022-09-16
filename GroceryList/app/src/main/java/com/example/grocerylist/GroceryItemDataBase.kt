package com.example.grocerylist

import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.grocerylist.GroceryItemDataBase.Companion.instance
import java.util.concurrent.locks.Lock

@Database(entities = [GroceryItems::class], version = 2)

abstract class GroceryItemDataBase : RoomDatabase() {

    abstract fun groceryDao(): GroceryDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var instance: GroceryItemDataBase? = null
        private val LOCK=Any()

        operator fun invoke(context: Context)= instance?: synchronized(LOCK){
            instance?:creareDataBase(context).also {
                instance=it
            }
        }

        private fun creareDataBase(context: Context) =
            Room.databaseBuilder(context.applicationContext,GroceryItemDataBase::class.java,"db").build()



    }



}