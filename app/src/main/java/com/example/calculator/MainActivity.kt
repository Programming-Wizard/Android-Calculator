package com.example.calculator

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import java.util.Scanner
import java.util.Stack

class MainActivity : AppCompatActivity() {
    private lateinit var button0: Button
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button
    private lateinit var button7: Button
    private lateinit var button8: Button
    private lateinit var button9: Button
    private lateinit var button00: Button
    private lateinit var buttonDot: Button
    private lateinit var buttonSolve: Button
    private lateinit var buttonAdd: Button
    private lateinit var buttonSub: Button
    private lateinit var buttonDivide: Button
    private lateinit var buttonMultiply: Button
    private lateinit var buttonModulus: Button
    private lateinit var buttonBack: Button
    private lateinit var buttonClear: Button
    private lateinit var tvResult: TextView
    private lateinit var tvInput: TextView
    private lateinit var tvCalculator: TextView
    public lateinit var tvCurrencyCov: TextView
    private lateinit var tvTempCov: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button0 = findViewById(R.id.button0)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)
        button7 = findViewById(R.id.button7)
        button8 = findViewById(R.id.button8)
        button9 = findViewById(R.id.button9)
        button00 = findViewById(R.id.buttonDouble0)
        buttonDot = findViewById(R.id.buttonDot)
        buttonSolve = findViewById(R.id.buttonSolve)
        buttonMultiply = findViewById(R.id.buttonMultiply)
        buttonDivide = findViewById(R.id.buttonDivide)
        buttonAdd = findViewById(R.id.buttonAdd)
        buttonSub = findViewById(R.id.buttonSub)
        buttonModulus = findViewById(R.id.buttonModulus)
        buttonBack = findViewById(R.id.buttonBack)
        buttonClear = findViewById(R.id.buttonClear)
        tvResult = findViewById(R.id.tvResult)
        tvInput = findViewById(R.id.tvInput)
        tvCalculator = findViewById(R.id.tvCalculator)
        tvCurrencyCov = findViewById(R.id.tvCurrencyCov)
        tvTempCov = findViewById(R.id.tvTempCov)


        val buttons = listOf<Button>(
            button0,
            button1,
            button2,
            button3,
            button4,
            button5,
            button6,
            button7,
            button8,
            button9,
            button00,
            buttonAdd,
            buttonSub,
            buttonMultiply,
            buttonDivide,
            buttonDot,
            buttonModulus
        )


        val CanClick = true;
        tvCalculator.setOnClickListener{
            tvCalculator.setTextColor(ContextCompat.getColor(this,R.color.md_theme_light_secondary))
            tvCurrencyCov.setTextColor(Color.WHITE)
            tvTempCov.setTextColor(Color.WHITE)
        }
        tvCurrencyCov.setOnClickListener{
            tvCurrencyCov.setTextColor(ContextCompat.getColor(this,R.color.md_theme_light_secondary))
            tvCalculator.setTextColor(Color.WHITE)
            tvTempCov.setTextColor(Color.WHITE)
            val currencyIntent = Intent(this, CurrencyActivity::class.java)
            startActivity(currencyIntent)
        }
        tvTempCov.setOnClickListener{
            tvTempCov.setTextColor(ContextCompat.getColor(this,R.color.md_theme_light_secondary))
            tvCurrencyCov.setTextColor(Color.WHITE)
            tvCalculator.setTextColor(Color.WHITE)
            val tempIntent = Intent(this, TemperatureActivity::class.java)
            startActivity(tempIntent)
        }
        buttonClear.setOnClickListener {
            tvInput.text = ""
            tvResult.text = "0"
        }
        buttonBack.setOnClickListener {
            if (tvInput.text.toString().isNotBlank()) tvInput.text =
                tvInput.text.toString().substring(0, tvInput.length() - 1)
            else println("null String")

        }

        buttonSolve.setOnClickListener {
//            handleSolving(tvInput.text.toString())
            var realResultProbably = stack_Implementation.solve(tvInput.text.toString())
            tvResult.text = realResultProbably.toString()
        }

        for (button in buttons) {
            button.setOnClickListener {
                handleButtonClick(button.text.toString())
            }
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "calculator channel name"
            val descriptionT = "not so very important notifications"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("calculator_channel_id", name, importance).apply { description = descriptionT }
            val notificationManager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
        val notification = NotificationCompat.Builder(this,"calculator_channel_id")
            .setSmallIcon(R.drawable.notification_logo)
            .setContentText("Start Calculating today and never get the results!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//            .setContentIntent()
            .setAutoCancel(true)
            .build()
        val notificationManager = NotificationManagerCompat.from(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
//            ActivityCompat.requestPermissions(this,,101)/
            return
        }
        notificationManager.notify(0,notification)
    }

    private fun handleButtonClick(input: String) {

        if (input == "." && tvInput.text.toString().contains(".")) {
            return
        }
        "${tvInput.text}$input".also { tvInput.text = it }
    }
}


object stack_Implementation {
    @JvmStatic
    public fun solve(exp :String): Int {
        var input = exp
        val OPstack = Stack<Char>()
        val Numstack = Stack<Int>()
        try {
            println("Enter an equation : ")
            //			String input = "15-6-9*5*8";
            println(input)
            input = input.replace(" ".toRegex(), "")
            var i = 0
            OuterMostLoop@ while (i < input.length) {
                var currentChar = input[i]
                if (currentChar == '+' || currentChar == '-' || currentChar == 'X' || currentChar == '÷') {
                    if (!OPstack.isEmpty() && OPstack.peek() == 'X') {
                        evaluateOperation(OPstack, Numstack)
                    }

//					while (!OPstack.isEmpty() && hasPrecedence(currentChar, OPstack.peek()) || !OPstack.isEmpty() && OPstack.peek() == '-' && currentChar == '-' || !OPstack.isEmpty() && ) {
//						evaluateOperation(OPstack, Numstack);
//						System.out.println("operator stack : "+OPstack);
//						System.out.println("number stack : "+Numstack);
//					}
                    OPstack.push(currentChar)
                    println("operator stack : $OPstack")
                } else if (Character.isDigit(currentChar)) {
                    var number = ""
                    var flag = i
                    while (Character.isDigit(currentChar)) {
                        number += currentChar
                        if (flag == input.length - 1) {
                            Numstack.push(number.toInt())
                            println("number stack : $Numstack")
                            break@OuterMostLoop
                        }
                        currentChar = input[++flag]
                    }
                    i = flag - 1
                    Numstack.push(number.toInt())
                    println("number stack : $Numstack")
                }
                i++
            }
            while (!OPstack.isEmpty()) {
                evaluateOperation(OPstack, Numstack)
            }
            if (!Numstack.isEmpty()) {
                println("Result :" + Numstack.pop())
                return result
            } else {
                println("Invalid expression.")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    private fun hasPrecedence(currentCharacter: Char, operatorInStack: Char): Boolean {
        return currentCharacter == 'X' || currentCharacter == '÷' && operatorInStack == '+' || operatorInStack == '-'
    }

    var result = 0
    private fun evaluateOperation(OPstack: Stack<Char>, Numstack: Stack<Int>) {
        val op = OPstack.pop()
        val op2 = Numstack.pop()
        val op1 = Numstack.pop()
        println("op1 : $op1")
        println("op2 : $op2")
        println("op : $op")
        when (op) {
            '+' -> result = op1 + op2
            '-' -> result = op1 - op2
            'X' -> result = op1 * op2
            '÷' -> if (op2 != 0) {
                result = op1 / op2
            } else {
                println("Error: Division by zero.")
            }
        }
        println("result : " + result)
        Numstack.push(result)
    }
}



