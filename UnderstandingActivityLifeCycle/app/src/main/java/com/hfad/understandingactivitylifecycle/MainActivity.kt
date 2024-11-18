package com.hfad.understandingactivitylifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import java.text.ChoiceFormat

class MainActivity : AppCompatActivity() {

    lateinit var chronometer: Chronometer
    var isRunning = false
    var offSet: Long = 0

    fun setBaseTime()
    {
        chronometer.base = SystemClock.elapsedRealtime() - offSet
    }

    fun saveOffSet()
    {
        offSet = SystemClock.elapsedRealtime() - chronometer.base
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chronometer = findViewById<Chronometer>(R.id.stopwatch)

        if (savedInstanceState != null)
        {
            isRunning = savedInstanceState.getBoolean("running")
            offSet = savedInstanceState.getLong("offset")
            if (isRunning)
            {
                chronometer.base = savedInstanceState.getLong("currentState")
                chronometer.start()
            }
            else
                setBaseTime()
        }

        val startButtonRef = findViewById<Button>(R.id.startButton)
        startButtonRef.setOnClickListener {
            if (!isRunning)
            {
                isRunning = true
                setBaseTime()
                chronometer.start()
            }
        }

        val pauseButtonRef = findViewById<Button>(R.id.pauseButton)
        pauseButtonRef.setOnClickListener {
            if (isRunning)
            {
                isRunning = false
                saveOffSet()
                chronometer.stop()
            }
        }

        val resetButtonRef = findViewById<Button>(R.id.resetButton)
        resetButtonRef.setOnClickListener {
            offSet = 0
            setBaseTime()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putLong("currentState", chronometer.base)
        outState.putLong("offset", offSet)
        outState.putBoolean("running", isRunning)
        super.onSaveInstanceState(outState)
    }

    /*Os métodos onPause() e onResume() são chamados sempre que uma activity fica invisivel e
    * fica visivel, respectivamente.
    *
    * Então, dessa forma, podemos implementar o comportamento de parar e continuar a contagem
    * do cronometro nesses dois métodos. */
    override fun onPause() {
        super.onPause()
        if (isRunning)
        {
            //isRunning = false
            saveOffSet()
            chronometer.stop()
        }
    }

    override fun onResume() {
        super.onResume()
        if (isRunning)
        {
            setBaseTime()
            chronometer.start()
        }
    }
//
//    override fun onStop() {
//        super.onStop()
//        if (isRunning)
//        {
//            //isRunning = false
//            saveOffSet()
//            chronometer.stop()
//        }
//    }
//
//    override fun onRestart() {
//        super.onRestart()
//        if (isRunning)
//        {
//            setBaseTime()
//            chronometer.start()
//        }
//    }

    /*Se eu implementar o código para que, quando a activity volte a estar visivel depois de estar
    * invisivel, no onStart(), pode dar problema, pois o método onStart() é chamado não apenas
    * nessa mudança de estado. Ao inicializarmos o app, ele tambem é chamado.
    *
    * No caso desse app, não da problema pois eu inicializo a variável isRunning como false. Então,
    * o cronometro não começa rodando.
    * Mas, se eu inicializar ela como true, o cronometro vai inicializar assim que abrirmos o app.
    *
    * POR ISSO QUE, nesses casos onde queremos implementar um comportamento quando o app volta a
    * estar visivel depois de ter ficado invisivel, PRECISAMOS implementar no método onRestart()!*/
//    override fun onStart() {
//        super.onStart()
//        if (isRunning)
//        {
//            setBaseTime()
//            chronometer.start()
//        }
//    }

//    private var baseTime: Long = 0
//    //SystemClock.elapsedRealtime()
//    private var isRunning: Boolean = false
//    private lateinit var chronometerRef: Chronometer
//    // = findViewById<Chronometer>(R.id.stopwatch)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val startButtonRef = findViewById<Button>(R.id.startButton)
//        val pauseButtonRef = findViewById<Button>(R.id.pauseButton)
//        val resetButtonRef = findViewById<Button>(R.id.resetButton)
//        chronometerRef = findViewById<Chronometer>(R.id.stopwatch)
//
//        var savedState: Long = 0
//        var savedBaseTime: Long = 0
//        var runningState: Boolean = false
//
//        if (savedInstanceState != null)
//        {
//            savedState = savedInstanceState.getLong("current")
//            runningState = savedInstanceState.getBoolean("running", false)
//            savedBaseTime = savedInstanceState.getLong("baseTime")
//            if (runningState)
//            {
//                chronometerRef.base = savedState
//                chronometerRef.start()
//            }
//            else
//                chronometerRef.base = SystemClock.elapsedRealtime() - savedState
//        }
//
//        isRunning = runningState
//        baseTime = savedState
//
//        startButtonRef.setOnClickListener() {
//            if (!isRunning)
//            {
//                isRunning = true
//                if (baseTime != 0.toLong())
//                {
//                    chronometerRef.base = SystemClock.elapsedRealtime() - baseTime
//                }
//                else
//                    chronometerRef.base = SystemClock.elapsedRealtime()
//                chronometerRef.start()
//            }
//        }
//
//        pauseButtonRef.setOnClickListener() {
//            if (isRunning)
//            {
//                isRunning = false
//                baseTime = SystemClock.elapsedRealtime() - chronometerRef.base
//                chronometerRef.stop()
//            }
//        }
//
//        resetButtonRef.setOnClickListener() {
//            baseTime = 0
//            chronometerRef.base = SystemClock.elapsedRealtime()
//        }
//    }
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        //outState.putLong("current", chronometerRef.base)
//        super.onSaveInstanceState(outState)
//        outState.putLong("baseTime", baseTime)
//        outState.putLong("current", chronometerRef.base)
//        outState.putBoolean("running", isRunning)
//    }

}