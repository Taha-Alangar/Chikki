package com.tahaalangar.paniwalaadmin

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        auth = FirebaseAuth.getInstance()
        Handler(Looper.getMainLooper()).postDelayed({
            if (auth.currentUser != null) {
                // User is signed in, navigate to MainActivity
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                // No user is signed in, navigate to LoginActivity
                startActivity(Intent(this, LoginActivity::class.java))
            }
            finish()
        }, 1200) // Splash screen delay duration
    }
}