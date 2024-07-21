package com.tahaalangar.loginsharedpreference

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        val appPreference=AppPreference(this)
        val userEmail=appPreference.getString("email","")

        val targetActivity = if (userEmail!!.isNotEmpty()) {

            MainActivity::class.java
        } else {
            LoginScreen::class.java
        }

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this,targetActivity))
            finish()
        }, 1000)
    }
}