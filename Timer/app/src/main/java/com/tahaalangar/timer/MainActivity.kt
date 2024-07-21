package com.tahaalangar.timer

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private lateinit var toggleButton: Button
    private lateinit var reset: Button
    private lateinit var timer: TextView

    private var isRunning = false
    private var timerSecond = 0
    private var handler = Handler(Looper.getMainLooper())
    private val runnable = object : Runnable {
        override fun run() {
            timerSecond++
            val hours = timerSecond / 3600
            val minutes = (timerSecond % 3600) / 60
            val seconds = timerSecond % 60

            val time = String.format("%02d:%02d:%02d", hours, minutes, seconds)
            timer.text = time

            handler.postDelayed(this, 1000)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toggleButton=findViewById(R.id.start)
        reset=findViewById(R.id.reset)
        timer=findViewById(R.id.timer)


        toggleButton.setOnClickListener {
            if (isRunning) {
                stopTimer()
            } else {
                startTimer()
            }
        }

        reset.setOnClickListener {
            resetTimer()
        }

    }


    private fun startTimer(){
        if (!isRunning){
            handler.postDelayed(runnable, 1000)
            isRunning = true
            toggleButton.text="Stop"
            reset.isEnabled=false
        }
    }

    private fun stopTimer(){
        if (isRunning){
            handler.removeCallbacks(runnable)
            isRunning = false
            toggleButton.isEnabled=true
            toggleButton.text = "Start"
            reset.isEnabled=true
        }
    }

    private fun resetTimer(){
        stopTimer()
        timerSecond = 0
        timer.text="00:00:00"

    }

}