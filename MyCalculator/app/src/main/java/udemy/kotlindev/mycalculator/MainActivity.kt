package udemy.kotlindev.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private var tvInput: TextView? = null
    private var lastNumeric: Boolean = false
    private var lastDot: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//putting a listener on every button  is not recommended by android
        tvInput = findViewById(R.id.tvInput)



    }



    fun onDigit(view: View){
      tvInput?.append((view as Button).text)
        lastNumeric= true
        lastDot= false
    }
    fun onClear(view:View){
        tvInput?.text=""
    }
    fun onDecimalPoint(view: View){
// to prevent duplication of the dot after one another. we'll use if statement
     if (lastNumeric && !lastDot){
        tvInput?.append(".")
         //flags
         lastNumeric= false
         lastDot=true
     }
    }
    fun onOperator(view: View){ // the whole idea is to prevent using two operators, we want to do one operation at a time
        tvInput?.text?.let{
//lambda automatiically created for us when we are using let &let used with nullable ^^
            if(lastNumeric && !isOperatorAdded(it.toString())){
                // we are checking if we have not already used an operator and we have a number, then...
                tvInput?.append((view as Button).text) //append the operator that have been clicked
                lastNumeric= false //set the flags
                lastDot=false
            }
        }

    }
    fun onEqual(view: View){ //Adding Subtraction-using split and substring methods on Strings
        if(lastNumeric){
       var tvValue= tvInput?.text.toString()
            var prefix= ""  //to solve the subtraction with negative numbers problem
            try{
                if(tvValue.startsWith("-")){
                    prefix="-"
                    tvValue=tvValue.substring(1)
                }
                if (tvValue.contains("-")){
                    val     splitValue= tvValue.split("-")
                    var one= splitValue[0] //99
                    var two=splitValue[1] //1

                    if (prefix.isNotEmpty()){
                        one=prefix+ one
                    }
                    // var result= one.toDouble()- two.toDouble()
                    tvInput?.text = removeZeroAfterDot((one.toDouble()- two.toDouble()).toString())
//you also cant add = after - if there is not a number before it ^^

                }else if (tvValue.contains("+")){
                    val     splitValue= tvValue.split("+")
                    var one= splitValue[0] //99
                    var two=splitValue[1] //1

                    if (prefix.isNotEmpty()){
                        one=prefix+ one
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble()+ two.toDouble()).toString())


                }else if (tvValue.contains("/")){
                    val     splitValue= tvValue.split("/")
                    var one= splitValue[0] //99
                    var two=splitValue[1] //1

                    if (prefix.isNotEmpty()){
                        one=prefix+ one
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble()/ two.toDouble()).toString())


                }else if (tvValue.contains("*")){
                    val     splitValue= tvValue.split("*")
                    var one= splitValue[0] //99
                    var two=splitValue[1] //1

                    if (prefix.isNotEmpty()){
                        one=prefix+ one
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble()* two.toDouble()).toString())


                }
            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }
    //to get rid of the ".0" on a result
    private fun  removeZeroAfterDot(result: String): String{
      var value = result
        if(result.contains(".0"))
            value = result.substring(0,result.length - 2)
        return value
    }

    //helping method we used it in onOperator ^^
    private fun isOperatorAdded(value: String) : Boolean{
     return if(value.startsWith("-")){ //allowing the negative numbers only
         false
     }else
      value.contains("/")
              || value.contains("*")
              || value.contains("+")
              || value.contains("-")

    }
}