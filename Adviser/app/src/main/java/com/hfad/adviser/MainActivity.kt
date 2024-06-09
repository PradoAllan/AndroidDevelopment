package com.hfad.adviser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun options(s: String) : String
        {
            if ("Amber" == s)
                return ("Allan\nPrado")
            else if ("Dark" == s)
                return ("Prado\nAllan")
            else if ("Light" == s)
                return ("42\nRio")
            else
                return ("aprado\n42Rio")
        }

        fun getOptions(s: String) : List<String>
        {
            return when (s)
            {
                "Light" -> listOf("Jail Pale Ale", "Lager Lite")
                "Amber" -> listOf("Jack Amber", "Red Moose")
                "Brown" -> listOf("Brown Bear Beer", "Bock Brownie")
                else -> listOf("Gout stout", "Dark Daniel")
            }
        }

        val buttonRef = findViewById<Button>(R.id.button)
        buttonRef.setOnClickListener {
            val spinnerRef = findViewById<Spinner>(R.id.spinner)
            val textRef = findViewById<TextView>(R.id.show)
            val content = spinnerRef.selectedItem
            //textRef.text = "${content}"
            //textRef.text = content.toString()
            //textRef.text = "teste: " + content.toString()
            //textRef.text = "test 2: $content"
            textRef.text = getOptions(content.toString()).reduce{str, item -> str + '\n' + item}
            //textRef.text = options(content.toString())
        }
    }
}