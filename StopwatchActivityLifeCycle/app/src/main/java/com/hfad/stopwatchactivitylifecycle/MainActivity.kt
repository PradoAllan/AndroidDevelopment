package com.hfad.stopwatchactivitylifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import org.jetbrains.annotations.Nullable

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

        //referencia ao pomodoro button
        val ref_mode = findViewById<Button>(R.id.change_status)
        val ref_input_amout = findViewById<EditText>(R.id.amount)
        val ref_pomodoro = findViewById<Button>(R.id.pomodoro)
        val ref_status = findViewById<TextView>(R.id.status)

        ref_pomodoro.setOnClickListener {
            var input : Long = ref_input_amout.text.toString().toLong()
            var value : Long = SystemClock.elapsedRealtime() + (input * 60000)
            setPomodoroTime(value)
        }

        ref_mode.setOnClickListener {
            ref_pause.callOnClick()
            ref_reset.callOnClick()
            ref_input_amout.isVisible = true
            ref_pomodoro.isVisible = true
            ref_chronometer.isCountDown = true
            ref_status.text = "Pomodoro Mode"
        }
    }

    fun setPomodoroTime(time : Long) {
        ref_chronometer.base = time
        ref_chronometer.start()
        running = true
        offset = 0
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