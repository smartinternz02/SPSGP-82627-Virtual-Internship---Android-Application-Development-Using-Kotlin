package com.example.grocerylist

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), GroceryItemClickInterface {
    private lateinit var recyclerView: RecyclerView
    private lateinit var addItem:FloatingActionButton
    private lateinit var list: List<GroceryItems>
    private lateinit var groceryAdapter: GroceryAdapter
    private lateinit var groceryViewModel: GroceryViewModel
    private lateinit var userWelcome:TextView
    private lateinit var unit:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recylerView)
        addItem = findViewById(R.id.addItem)
        userWelcome=findViewById(R.id.userWelcome)
        list = ArrayList<GroceryItems>()
        groceryAdapter = GroceryAdapter(list, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = groceryAdapter
        val groceryItemRepo = GroceryItemRepo(GroceryItemDataBase.invoke(this))
        val factory = GroceryViewModelFactory(groceryItemRepo)
        groceryViewModel = ViewModelProvider(this, factory).get(GroceryViewModel::class.java)
        groceryViewModel.getAllGroceryItems().observe(this, Observer {
            groceryAdapter.List = it
            groceryAdapter.notifyDataSetChanged()

        })
        addItem.setOnClickListener {
            createDialogBox()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun createDialogBox() {

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_box_to_add)
        val itemName = dialog.findViewById<EditText>(R.id.etItemName)
        val itemPrice = dialog.findViewById<EditText>(R.id.etItemRate)
        val itemQuantity = dialog.findViewById<EditText>(R.id.etItemQuantity)
        val cancelBtn = dialog.findViewById<Button>(R.id.cancelBtn)
        val addBtn = dialog.findViewById<Button>(R.id.addBtn)
        val unitSp= dialog.findViewById<Spinner>(R.id.unitSp)
        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
        unitSp.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
               unit=adapterView?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        addBtn.setOnClickListener {
            userWelcome.visibility= View.INVISIBLE
            val name = itemName.text.toString()
            val price = itemPrice.text.toString()
            val count = itemQuantity.text.toString()
            when {
                name.isEmpty() -> {
                    val builder =
                        AlertDialog.Builder(this).setIcon(R.drawable.ic_baseline_warning_24)
                            .setTitle("WARNING !")
                            .setMessage("Item Name Can't be Empty")
                            .setPositiveButton("Ok") { _, _ ->
                                Toast.makeText(
                                    applicationContext,
                                    "Provide a proper Name of Item",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }.create()
                    builder.show()
                }
                price.isEmpty() -> {
                    val builder =
                        AlertDialog.Builder(this).setIcon(R.drawable.ic_baseline_warning_24)
                            .setTitle("WARNING !")
                            .setMessage("Item Price Can't be Empty")
                            .setPositiveButton("Ok") { _, _ ->
                                Toast.makeText(
                                    applicationContext,
                                    "Provide a proper Value of Item Price",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }.create()
                    builder.show()
                }
                count.isEmpty() -> {
                    val builder =
                        AlertDialog.Builder(this).setIcon(R.drawable.ic_baseline_warning_24)
                            .setTitle("WARNING !")
                            .setMessage("Item Number Can't be Empty")
                            .setPositiveButton("Ok") { _, _ ->
                                Toast.makeText(
                                    applicationContext,
                                    "Provide a proper value  of Item Number.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }.create()
                    builder.show()
                }
                else -> {
                    val items = GroceryItems(name, count.toInt(), price.toDouble(),unit)
                    groceryViewModel.insert(items)
                    Toast.makeText(
                        this,
                        "SuccessFully added  $name to Grocery List.",
                        Toast.LENGTH_LONG
                    ).show()
                    groceryAdapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
            }
        }
        dialog.show()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onItemClick(groceryItems: GroceryItems) {
        groceryViewModel.delete(groceryItems)
        groceryAdapter.notifyDataSetChanged()
        Toast.makeText(applicationContext, "Item deleted", Toast.LENGTH_SHORT).show()
    }
}