package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

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

        buttonClear.setOnClickListener {
            tvInput.text = ""
            tvResult.text = "0"
        }
        buttonBack.setOnClickListener {
            if (tvInput.text.toString().isNotBlank()) tvInput.text =
                tvInput.text.toString().substring(0, tvInput.length() - 1)
            else println("null String")

        }

        buttonSolve.setOnClickListener{
            handleSolving(tvInput.text.toString())
        }

        for (button in buttons) {
            button.setOnClickListener {
                handleButtonClick(button.text.toString())
            }
        }


    }

    private fun handleButtonClick(input: String) {
        if (input == "." && tvInput.text.toString().contains(".")) {
            return
        }
        "${tvInput.text}$input".also { tvInput.text = it }
    }

    private fun handleSolving(getEqn: String) {
        if (getEqn.contains("+")) {
            val op1 = getEqn.substringBefore("+").toInt()
            val op2 = getEqn.substringAfter("+").toInt()
            val result: Int = op1 + op2
            tvResult.text = result.toString()
        }
        else if (getEqn.contains("-")) {
            val op1 = getEqn.substringBefore("-").toInt()
            val op2 = getEqn.substringAfter("-").toInt()
            val result: Int = op1 - op2
            tvResult.text = result.toString()
        }
        else if (getEqn.contains("X")) {
            val op1 = getEqn.substringBefore("X").toInt()
            val op2 = getEqn.substringAfter("X").toInt()
            val result: Int = op1 * op2
            tvResult.text = result.toString()
        }
        else if (getEqn.contains("%")) {
            val op1 = getEqn.substringBefore("%").toInt()
            val op2 = getEqn.substringAfter("%").toInt()
            val result: Int = op1 % op2
            tvResult.text = result.toString()
        }
        else if (getEqn.contains("÷")) {
            val op1 = getEqn.substringBefore("÷").toInt()
            val op2 = getEqn.substringAfter("÷").toInt()
            try {
                val result: Int = op1 / op2
                tvResult.text = result.toString()

            } catch (e: ArithmeticException) {
                println("error")
            }

        }
    }
}