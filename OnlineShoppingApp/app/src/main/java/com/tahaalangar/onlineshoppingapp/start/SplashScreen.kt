package com.tahaalangar.onlineshoppingapp.start

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tahaalangar.onlineshoppingapp.R
import com.tahaalangar.onlineshoppingapp.auth.AppPreference
import com.tahaalangar.onlineshoppingapp.auth.LoginScreen
import com.tahaalangar.onlineshoppingapp.auth.SignUpScreen
import com.tahaalangar.onlineshoppingapp.screens.MainActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        val appPreference = AppPreference(this)
        val isFirstLaunch = appPreference.getBoolean("is_first_launch", true)
        val isSignedUp = appPreference.getBoolean("is_signed_up", false)

        val targetActivity = when {
            isFirstLaunch -> OnBoardingScreen::class.java
            isSignedUp -> MainActivity::class.java
            else -> LoginScreen::class.java
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, targetActivity).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
            finish()
        }, 1000)
    }
}