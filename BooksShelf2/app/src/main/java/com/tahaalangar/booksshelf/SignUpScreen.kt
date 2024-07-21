package com.tahaalangar.booksshelf

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignUpScreen : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_screen)

        val signUpBtn=findViewById<TextView>(R.id.signup_btn)
        val loginTV=findViewById<TextView>(R.id.loginTV)

        signUpBtn.setOnClickListener {
            startActivity(Intent(this,LoginScreen::class.java))
        }
        loginTV.setOnClickListener {
            startActivity(Intent(this,LoginScreen::class.java))
        }
    }
}