package com.tahaalangar.firebaseproject

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    val CHANNEL_ID = "GFG"
    val CHANNEL_NAME = "GFG ContentWriting"
    val CHANNEL_DESCRIPTION = "GFG NOTIFICATION"

    lateinit var et1: EditText
    lateinit var et2: EditText
    lateinit var btn1: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        // Binding Views by their IDs
        et1 = findViewById(R.id.titleTV)
        et2 = findViewById(R.id.description)
        btn1 = findViewById(R.id.send)

        btn1.setOnClickListener {

         notificationChannel()

            val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(et1.text.toString())
                .setContentText(et2.text.toString())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()



            // finally notifying the notification
            val nManager = NotificationManagerCompat.from(this)
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return@setOnClickListener

            }
            nManager.notify(1, builder)

            et1.text.clear()
            et2.text.clear()
        }
    }

    // Creating the notification channel
    private fun notificationChannel() {
        // check if the version is equal or greater
        // than android oreo version
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // creating notification channel and setting
            // the description of the channel
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = CHANNEL_DESCRIPTION
            }
            // registering the channel to the System
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}