package com.hfad.moneyconvertortwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    var moneyArray = arrayOf("USD", "CAD", "EUR", "BRL")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val refSpinner = findViewById<Spinner>(R.id.currency)


    }
}