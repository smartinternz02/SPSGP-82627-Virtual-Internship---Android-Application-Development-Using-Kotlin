package com.example.happybirthday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val userName=intent.getStringExtra("USER")
        val textView = findViewById<TextView>(R.id.wishing)
        val greetingText="Happy BirthDay !, $userName 🎉"
        textView.text=greetingText
    }
}