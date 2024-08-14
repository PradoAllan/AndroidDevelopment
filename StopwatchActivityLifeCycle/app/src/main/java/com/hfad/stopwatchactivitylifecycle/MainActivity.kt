package com.hfad.stopwatchactivitylifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {

    lateinit var ref_chronometer : Chronometer
    var running : Boolean = false
    var offset : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //referencia à View chronometro
        ref_chronometer = findViewById<Chronometer>(R.id.chronometer)

        //referencia ao button start
        val ref_start = findViewById<Button>(R.id.start)
        ref_start.setOnClickListener {
            if (!running) {
                setBaseTime()
                ref_chronometer.start()
                running = true
            }
        }

        //referencia ao button pause
        val ref_pause = findViewById<Button>(R.id.pause)
        ref_pause.setOnClickListener {
            if (running) {
                saveOffset()
                ref_chronometer.stop()
                running = false
            }
        }

        //referencia ao button reset
        val ref_reset = findViewById<Button>(R.id.reset)
        ref_reset.setOnClickListener {
            offset = 0
            setBaseTime()
        }
    }

    //atualiza o ref_chronometer.base time
    fun setBaseTime() {
        ref_chronometer.base = SystemClock.elapsedRealtime() - offset
    }

    //salva o offset
    fun saveOffset() {
        offset = SystemClock.elapsedRealtime() - ref_chronometer.base
    }

}