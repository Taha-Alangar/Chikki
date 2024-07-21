package com.tahaalangar.time

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import java.util.Timer
import java.util.TimerTask

class TimerService: Service() {

    override fun onBind(p0: Intent?): IBinder? =null

    private val timer = Timer()
    private var time = 0.0

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int
    {
        time = intent.getDoubleExtra(TIME_EXTRA, 0.0)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }

        val notification = createNotification()
        startForeground(NOTIFICATION_ID, notification)

        timer.schedule(TimeTask(time), 0, 1000)
        return START_STICKY
    }
    override fun onDestroy()
    {
        timer.cancel()
        super.onDestroy()
    }

    private inner class TimeTask(private var time: Double) : TimerTask()
    {
       override fun run()
        {
            val intent = Intent(TIMER_UPDATED)
            time++
            intent.putExtra(TIME_EXTRA, time)
            sendBroadcast(intent)
        }
    }

    private fun createNotification(): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Timer Service")
            .setContentText("Timer is running...")
            .setSmallIcon(R.drawable.ic_launcher_foreground) // You can replace this with your app's icon
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Timer Service Channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(serviceChannel)
        }
    }
    companion object
    {
        const val TIMER_UPDATED = "timerUpdated"
        const val TIME_EXTRA = "timeExtra"
        private const val CHANNEL_ID = "TimerServiceChannel"
        private const val NOTIFICATION_ID = 1
    }

}