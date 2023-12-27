package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class TemperatureActivity : AppCompatActivity() {
    private lateinit var etCelciusInput: EditText
    private lateinit var etFarenheitInput: EditText
    private lateinit var ivBackButton2: ImageView
    private lateinit var convertButton: Button
    private lateinit var clearButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.temperature_activity)
        etCelciusInput = findViewById(R.id.etCelciusInput)
        etFarenheitInput = findViewById(R.id.etFarenheitInput)
        ivBackButton2 = findViewById(R.id.ivBackButton2)
        convertButton = findViewById(R.id.ConvertButton)
        clearButton = findViewById(R.id.clearButton)

        ivBackButton2.setOnClickListener {
            finish()
        }
        convertButton.setOnClickListener {
            convert()
        }
        clearButton.setOnClickListener{
            etCelciusInput.setText("")
            etFarenheitInput.setText("")
        }
    }

    private fun convert() {
        var CelciusInput = etCelciusInput.text.toString()
        var FarenheitInput = etFarenheitInput.text.toString()

        if (CelciusInput.isBlank() && FarenheitInput.isBlank()) {
            return
        } else if (CelciusInput.isEmpty()) {
            var input = etFarenheitInput.text.toString()
            var result = (input.toDouble() - 32) * 5 / 9
            etCelciusInput.setText(result.toString())
        } else if (FarenheitInput.isEmpty()) {
            var input = etCelciusInput.text.toString()
            var result = input.toDouble() * (9 / 5) + 32
            etFarenheitInput.setText(result.toString())
        }
    }

}