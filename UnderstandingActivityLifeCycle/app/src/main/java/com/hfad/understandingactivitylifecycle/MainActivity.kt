package com.hfad.understandingactivitylifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {

    private var baseTime: Long = 0
    //SystemClock.elapsedRealtime()
    private var isRunning: Boolean = false
    private lateinit var chronometerRef: Chronometer
    // = findViewById<Chronometer>(R.id.stopwatch)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButtonRef = findViewById<Button>(R.id.startButton)
        val pauseButtonRef = findViewById<Button>(R.id.pauseButton)
        val resetButtonRef = findViewById<Button>(R.id.resetButton)
        chronometerRef = findViewById<Chronometer>(R.id.stopwatch)

        var savedState: Long = 0
        var savedBaseTime: Long = 0
        var runningState: Boolean = false

        if (savedInstanceState != null)
        {
            savedState = savedInstanceState.getLong("current")
            runningState = savedInstanceState.getBoolean("running", false)
            savedBaseTime = savedInstanceState.getLong("baseTime")
            if (runningState)
            {
                chronometerRef.base = savedState
                chronometerRef.start()
            }
            else
                chronometerRef.base = baseTime
        }

        isRunning = runningState
        baseTime = savedState

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

    override fun onSaveInstanceState(outState: Bundle) {
        //outState.putLong("current", chronometerRef.base)
        super.onSaveInstanceState(outState)
        outState.putLong("baseTime", baseTime)
        outState.putLong("current", chronometerRef.base)
        outState.putBoolean("running", isRunning)
    }

}