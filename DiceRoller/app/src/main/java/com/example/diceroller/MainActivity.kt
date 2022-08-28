package com.example.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

//This is for to make user able to show dice and roll it and got the result.
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button =
            findViewById(R.id.button)  //this is for track the button and make the action when button is clicked
        rollButton.setOnClickListener {
            //    this is for pop up a massage when button is clicked
            val toast = Toast.makeText(this, "Dice Rolled!", Toast.LENGTH_SHORT)
            toast.show()
            rollDice()
        }
        rollDice()   //while app open user should be able to see the ui first.
    }

    //  this function is for update the result after rolling
    private fun rollDice() {

        //this is for 1st dice

        val myFirstDice = Dice(6)
        val diceRollOne = myFirstDice.roll()

//        for text view in the app

//        val resultTextViewOne: TextView = findViewById(R.id.textView)
//        resultTextViewOne.text = diceRollOne.toString()

//        for image view in teh app
        val diceImage: ImageView = findViewById(R.id.imageView)
        val resultTextViewOne: TextView = findViewById(R.id.textView)

        val drawableResource = when (diceRollOne) {
            1-> R.drawable.dice_1
            2-> R.drawable.dice_2
            3-> R.drawable.dice_3
            4-> R.drawable.dice_4
            5-> R.drawable.dice_5
            else-> R.drawable.dice_6
        }
        diceImage.setImageResource(drawableResource)
        diceImage.contentDescription = diceRollOne.toString()
        resultTextViewOne.text = diceRollOne.toString()

        //this is for second dice

//        val mySecondDice = Dice(20)
//        val diceRollTow = mySecondDice.roll()
//        val resultTextViewTow: TextView = findViewById(R.id.textView3)
//        resultTextViewTow.text = diceRollTow.toString()


    }
}

//class which encapsulate all the variables and function called inside the main activity function
class Dice(private val sides: Int) {
    fun roll(): Int {
        return (1..sides).random()
    }
}