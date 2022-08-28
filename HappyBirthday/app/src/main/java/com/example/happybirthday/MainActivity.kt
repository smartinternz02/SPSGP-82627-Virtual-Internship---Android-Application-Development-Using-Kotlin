package com.example.happybirthday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val greetingText = findViewById<TextView>(R.id.wishing)
//        val greet = findViewById<TextView>(R.id.greet)
//        val wisherName = findViewById<TextView>(R.id.wisher)
        val inputText = findViewById<EditText>(R.id.personName)
//        val image = findViewById<ImageView>(R.id.imageView)
        val button= findViewById<Button>(R.id.submitBtn)
        button.setOnClickListener{
            val enteredName = inputText.text.toString()
            if(enteredName!="") {
                val intent = Intent(this,SecondActivity::class.java)
                intent.putExtra("USER",enteredName)
                startActivity(intent)
//                val greetings = "Happy Birthday! , $enteredName ."
//                greetingText.text = greetings
//            greet.setText(getString(R.string.greet))
//            image.setImageResource(R.drawable.slashio_photography_gkcoau1esxg_unsplash)
//            wisherName.setText(getString(R.string.wisher_name))
//            button.setVisibility(View.INVISIBLE)
//            inputText.setVisibility(View.INVISIBLE)}
            }
            else
            {
                val text="Error! Please enter one valid  name"
                val duration = Toast.LENGTH_SHORT
                val toast=Toast.makeText(applicationContext,text,duration)
                toast.show()
            }

        }

    }
}








