package com.hfad.moneyconvertor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //fun Double.format(digits: Int) = "%.${digits}f".format(this)

        val refBtn = findViewById<Button>(R.id.button)
        val refSpinner = findViewById<Spinner>(R.id.spinner)
        val refResult = findViewById<TextView>(R.id.result)
        val refValue = findViewById<TextView>(R.id.value)

        fun getExchange(coin: String, value: Double) : Double
        {
            return when (coin)
            {
                "USD" -> value * 0.18
                "CAD" -> value * 0.25
                else -> value * 0.17
            }
        }

        refBtn.setOnClickListener{
            val newValue : Double
            val temp = refValue.text.toString()

            newValue = getExchange(refSpinner.selectedItem.toString(), temp.toDouble())
            //refResult.text = newValue.format(2)
            refResult.text = "%.2f".format(newValue)
        }

    }
}