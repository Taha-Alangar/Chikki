package com.tahaalangar.stopwatch

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tahaalangar.stopwatch.databinding.ActivityMainBinding
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var timerStarted = mutableMapOf<String, Boolean>()
    private lateinit var serviceIntent: Intent
    private var times = mutableMapOf<String, Double>()
    private val timerIds = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("TimerPrefs", Context.MODE_PRIVATE)

        timerIds.forEach { timerId ->
            timerStarted[timerId] = sharedPreferences.getBoolean("${timerId}_started", false)
            times[timerId] = sharedPreferences.getFloat("${timerId}_time", 0.0f).toDouble()
            val startStopButtonId = resources.getIdentifier("startStopButton_$timerId", "id", packageName)
            findViewById<Button>(startStopButtonId).setOnClickListener { startStopTimer(timerId) }
        }

        serviceIntent = Intent(applicationContext, TimerService::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATED),
                    RECEIVER_EXPORTED
                )
            }
        }

        updateUI()
    }

    private fun startStopTimer(timerId: String) {
        if (timerStarted[timerId] == true) {
            stopTimer(timerId)
        } else {
            startTimer(timerId)
        }
    }

    private fun startTimer(timerId: String) {
        serviceIntent.putExtra(TimerService.TIME_EXTRA, times[timerId])
        serviceIntent.putExtra(TimerService.TIMER_ID, timerId)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent)
        } else {
            startService(serviceIntent)
        }
        timerStarted[timerId] = true
        updateButton(timerId)
        saveTimerState(timerId)
    }

    private fun stopTimer(timerId: String) {
        stopService(serviceIntent)
        timerStarted[timerId] = false
        updateButton(timerId)
        saveTimerState(timerId)
    }

    private val updateTime: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val timerId = intent.getStringExtra(TimerService.TIMER_ID) ?: return
            times[timerId] = intent.getDoubleExtra(TimerService.TIME_EXTRA, 0.0)
            saveTimerState(timerId)
            updateUI()
        }
    }

    private fun updateUI() {
        var totalTime = 0.0
        timerIds.forEach { timerId ->
            val timeTVId = resources.getIdentifier("timeTV_$timerId", "id", packageName)
            findViewById<TextView>(timeTVId).text = getTimeStringFromDouble(times[timerId]!!)
            totalTime += times[timerId]!!
            updateButton(timerId)
        }
        binding.totalTimeTV.text = getTimeStringFromDouble(totalTime)
    }

    private fun updateButton(timerId: String) {
        val startStopButtonId = resources.getIdentifier("startStopButton_$timerId", "id", packageName)
        val startStopButton = findViewById<Button>(startStopButtonId)
        startStopButton.text = if (timerStarted[timerId] == true) "Stop" else "Start"
    }

    private fun getTimeStringFromDouble(time: Double): String {
        val resultInt = time.roundToInt()
        val hours = resultInt % 86400 / 3600
        val minutes = resultInt % 86400 % 3600 / 60
        val seconds = resultInt % 86400 % 3600 % 60

        return makeTimeString(hours, minutes, seconds)
    }

    private fun makeTimeString(hour: Int, min: Int, sec: Int): String =
        String.format("%02d:%02d:%02d", hour, min, sec)

    private fun saveTimerState(timerId: String) {
        with(sharedPreferences.edit()) {
            putBoolean("${timerId}_started", timerStarted[timerId] == true)
            putFloat("${timerId}_time", times[timerId]!!.toFloat())
            apply()
        }
    }
}