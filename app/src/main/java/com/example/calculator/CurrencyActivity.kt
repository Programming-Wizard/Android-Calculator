package com.example.calculator

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CurrencyActivity : AppCompatActivity() {
    private lateinit var ivBackButton : ImageView
    private lateinit var convertButton : Button
    private lateinit var ClearButton : Button
    private lateinit var etIndianInput : EditText
    private lateinit var etDollarInput : EditText
    private lateinit var tvCurrencyCov : TextView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.currency_activity)
        convertButton = findViewById(R.id.convertButton)
        ClearButton = findViewById(R.id.ClearButton)
        ivBackButton = findViewById(R.id.ivBackButton)
        etIndianInput = findViewById(R.id.etIndianInput)
        etDollarInput = findViewById(R.id.etDollarInput)

        convertButton.setOnClickListener{
            convert()
        }

        ivBackButton.setOnClickListener{
            finish()
        }
        ClearButton.setOnClickListener{
            etIndianInput.setText("")
            etDollarInput.setText("")
        }

    }

    private fun convert(){
        var IndianInput = etIndianInput.text.toString()
        var DollarInput = etDollarInput.text.toString()

        if(IndianInput.isEmpty()){
                var input = etDollarInput.text.toString()
                val inrPrice : Double = 83.17
                val result : Double = input.toDouble() * inrPrice
                val newResult = "%.2f".format(result)
                etIndianInput.setText(newResult)
        }

        else if(DollarInput.isEmpty()){
                var input = etIndianInput.text.toString()
                val dollarPrice : Double = 0.12
                val result : Double = input.toDouble() * dollarPrice
                val newResult = "%.2f".format(result)
                etDollarInput.setText(newResult)
        }
        else if(DollarInput.isBlank() && IndianInput.isBlank()){
            return
        }


    }
}