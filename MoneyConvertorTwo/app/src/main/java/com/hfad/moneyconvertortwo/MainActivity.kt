package com.hfad.moneyconvertortwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private fun Double.format(digits: Int) = "%.${digits}f".format(this)
    private fun getMoneyValue(s: String) : Double {
        return when (s) {
            "USD" -> (0.18)
            "CAD" -> (0.25)
            "EUR" -> (0.17)
            "GBP" -> (0.14)
            else -> (0.0)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val refSpinner = findViewById<Spinner>(R.id.currency)
        val refButton = findViewById<Button>(R.id.convert)
        val refAmout = findViewById<EditText>(R.id.inputValue)
        val refResult = findViewById<TextView>(R.id.result)
        val refCalc = findViewById<TextView>(R.id.example)
        refButton.setOnClickListener {
            var result : Double

            result = ((refAmout.text.toString().toDouble()) * getMoneyValue(refSpinner.selectedItem.toString()))
            refCalc.text = "Conversion: ${refAmout.text} * ${getMoneyValue(refSpinner.selectedItem.toString()).toString()}"
            refResult.text = "Final value: ${result.format(2).toString()}"
        }
    }
}