package com.tahaalangar.booksshelf

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import java.util.Timer
import java.util.TimerTask

class TimerService: Service()  {
    private val timer = Timer()
    private var timerId: String = ""

    override fun onBind(intent: Intent): IBinder? = null

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        timerId = intent.getStringExtra(TIMER_ID) ?: ""
        val time = intent.getDoubleExtra(TIME_EXTRA, 0.0)
        timer.schedule(TimeTask(time), 0, 1000)
        startForegroundServiceWithNotification()
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        timer.cancel()
        super.onDestroy()
    }

    private inner class TimeTask(private var time: Double) : TimerTask() {
        override fun run() {
            val intent = Intent(TIMER_UPDATED)
            time++
            intent.putExtra(TIME_EXTRA, time)
            intent.putExtra(TIMER_ID, timerId)
            sendBroadcast(intent)
        }
    }

    private fun startForegroundServiceWithNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Timer Service",
                NotificationManager.IMPORTANCE_LOW
            )
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
            val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Timer Running")
                .setContentText("Your timer is running in the background.")
                .build()
            startForeground(1, notification)
        } else {
            val notification = NotificationCompat.Builder(this)
                .setContentTitle("Timer Running")
                .setContentText("Your timer is running in the background.")
                .build()
            startForeground(1, notification)
        }
    }

    companion object {
        const val TIMER_UPDATED = "timerUpdated"
        const val TIME_EXTRA = "timeExtra"
        const val TIMER_ID = "timerId"
        private const val CHANNEL_ID = "TimerServiceChannel"
    }
}