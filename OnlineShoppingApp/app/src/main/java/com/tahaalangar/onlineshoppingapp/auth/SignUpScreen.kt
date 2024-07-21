package com.tahaalangar.onlineshoppingapp.auth

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tahaalangar.onlineshoppingapp.R

class SignUpScreen : AppCompatActivity() {
    private lateinit var appPreference: AppPreference
    private lateinit var userName: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var buttonSignup: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_screen)


        appPreference = AppPreference(this)
        userName = findViewById(R.id.editTextTextUsername)
        email = findViewById(R.id.editTextEmailSignup)
        password = findViewById(R.id.editTextTextPasswordSignUp)
        confirmPassword = findViewById(R.id.editTextTextConfirmPassword)
        val signup = findViewById<Button>(R.id.buttonRegister)



        signup.setOnClickListener {
            val usernameText = userName.text.toString().trim()
            val emailText = email.text.toString().trim()
            val passwordText = password.text.toString().trim()
            val confirmPasswordText = confirmPassword.text.toString().trim()

            if (usernameText.isEmpty() || emailText.isEmpty() || passwordText.isEmpty() || confirmPasswordText.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (passwordText == confirmPasswordText) {
                appPreference.saveString("username", usernameText)
                appPreference.saveString("email", emailText)
                appPreference.saveString("password", passwordText)

                Log.d("SignUpScreen", "Saved email: $emailText, Saved password: $passwordText")

                appPreference.saveBoolean("is_signed_up", true)

                val intent = Intent(this, LoginScreen::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                finish()
            } else {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            }
        }

    }
}