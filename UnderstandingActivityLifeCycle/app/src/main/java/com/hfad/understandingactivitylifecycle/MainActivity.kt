package com.hfad.understandingactivitylifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButtonRef = findViewById<Button>(R.id.startButton)
        val pauseButtonRef = findViewById<Button>(R.id.pauseButton)
        val resetButtonRef = findViewById<Button>(R.id.resetButton)
        val chronometerRef = findViewById<Chronometer>(R.id.stopwatch)

        var baseTime: Long = 0
        //SystemClock.elapsedRealtime()
        var isRunning: Boolean = false

        startButtonRef.setOnClickListener() {
            if (!isRunning)
            {
                isRunning = true
                if (baseTime != 0.toLong())
                {
                    chronometerRef.base = SystemClock.elapsedRealtime() - baseTime
                }
                else
                    chronometerRef.base = SystemClock.elapsedRealtime()
                chronometerRef.start()
            }
        }

        pauseButtonRef.setOnClickListener() {
            if (isRunning)
            {
                isRunning = false
                baseTime = SystemClock.elapsedRealtime() - chronometerRef.base
                chronometerRef.stop()
            }
        }

        resetButtonRef.setOnClickListener() {
            baseTime = 0
            chronometerRef.base = SystemClock.elapsedRealtime()
        }
    }
}