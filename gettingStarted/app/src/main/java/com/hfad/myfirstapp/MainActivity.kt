package com.hfad.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val text = findViewById<TextView>(R.id.textView)
        val myName = findViewById<EditText>(R.id.editTextText)
        val sendButton = findViewById<Button>(R.id.button)
        sendButton.setOnClickListener {
            text.text = "Hello, ${myName.text}!"
            /*
            var oldText = text.text
            var input = myName.text
            var newText = "$oldText $input"
            text.text = newText
             */
        }
    }
}