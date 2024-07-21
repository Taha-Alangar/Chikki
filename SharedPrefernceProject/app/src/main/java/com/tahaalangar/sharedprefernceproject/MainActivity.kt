package com.tahaalangar.sharedprefernceproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var appPreference: AppPreference
    private lateinit var emailTv: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appPreference=AppPreference(this)

        emailTv=findViewById(R.id.emailTv)

        val userEmail = appPreference.getString("email", "defaultEmail")

        emailTv.text=userEmail

    }
}