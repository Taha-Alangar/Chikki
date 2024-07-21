package com.tahaalangar.timer

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.widget.Toast
import androidx.core.app.NotificationCompat
import java.util.Timer
import java.util.TimerTask

class TimerService : Service() {

    companion object {
        const val ACTION_START = "com.tahaalangar.timer.ACTION_START"
        const val ACTION_STOP = "com.tahaalangar.timer.ACTION_STOP"
    }
    private var timerSecond = 0
    private var isRunning = false
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var runnable: Runnable

    override fun onCreate() {
        super.onCreate()

        runnable = object : Runnable {
            override fun run() {
                if (isRunning) {
                    timerSecond++
                    val hours = timerSecond / 3600
                    val minutes = (timerSecond % 3600) / 60
                    val seconds = timerSecond % 60

                    val time = String.format("%02d:%02d:%02d", hours, minutes, seconds)
                    val intent = Intent("com.tahaalangar.timer.TIMER_UPDATED")
                    intent.putExtra("time", time)
                    sendBroadcast(intent)

                    handler.postDelayed(this, 1000)
                }
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> startTimer()
            ACTION_STOP -> stopTimer()
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun startTimer() {
        if (!isRunning) {
            isRunning = true
            handler.post(runnable)
        }
    }

    private fun stopTimer() {
        if (isRunning) {
            isRunning = false
            handler.removeCallbacks(runnable)
        }
    }
}