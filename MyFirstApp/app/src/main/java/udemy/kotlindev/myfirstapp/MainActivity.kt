package udemy.kotlindev.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       //reference to the button
        val btnClickMe= findViewById<Button>(R.id.mybutton)
        //reference to the text
        val txt=findViewById<TextView>(R.id.textView)
        //Variables that can be reassigned use the "var" keyword
        var countClicks=0

        //set on-click listener. If the button is clicked, there are some changes will be done
        btnClickMe.setOnClickListener {
            //increment by 1
            countClicks+=1
            btnClickMe.text ="Haha you clicked me!"
            //countClicks is a number it has to be string first so we can assign it as text to txt
             txt.text=countClicks.toString()
            Toast.makeText(this, "^-^! ♡", Toast.LENGTH_SHORT).show()
        }
        }


}