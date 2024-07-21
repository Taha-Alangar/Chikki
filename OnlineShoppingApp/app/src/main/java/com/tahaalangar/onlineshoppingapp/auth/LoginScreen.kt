package com.tahaalangar.onlineshoppingapp.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tahaalangar.onlineshoppingapp.R
import com.tahaalangar.onlineshoppingapp.screens.MainActivity

class LoginScreen : AppCompatActivity() {

    private lateinit var appPreference: AppPreference
    private lateinit var email: EditText
    private lateinit var password: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        appPreference=AppPreference(this)
        email=findViewById(R.id.editTextTextEmailAddress)
        password=findViewById(R.id.editTextTextPassword)
        val login:Button=findViewById(R.id.btn_login)
        val register: TextView=findViewById(R.id.registerText)

        register.setOnClickListener {
            startActivity(Intent(this,SignUpScreen::class.java))
        }

        login.setOnClickListener {
            val enteredEmail = email.text.toString().trim()
            val enteredPassword = password.text.toString().trim()

            if (enteredEmail.isEmpty() || enteredPassword.isEmpty()) {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val savedEmail = appPreference.getString("email", "")
            val savedPassword = appPreference.getString("password", "")

            Log.d("LoginScreen", "Entered email: $enteredEmail, Entered password: $enteredPassword")
            Log.d("LoginScreen", "Saved email: $savedEmail, Saved password: $savedPassword")

            if (enteredEmail == savedEmail && enteredPassword == savedPassword) {
                val intent = Intent(this, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }
}