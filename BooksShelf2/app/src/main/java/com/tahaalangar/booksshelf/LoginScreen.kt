package com.tahaalangar.booksshelf

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        val signUpTV=findViewById<TextView>(R.id.signup_tv)
        val loginBnt=findViewById<TextView>(R.id.login_btn)

        signUpTV.setOnClickListener {
            startActivity(Intent(this,SignUpScreen::class.java))
        }
        loginBnt.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}