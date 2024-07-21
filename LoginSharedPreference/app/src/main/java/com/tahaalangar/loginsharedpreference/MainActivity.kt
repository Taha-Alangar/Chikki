package com.tahaalangar.loginsharedpreference

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var appPreference: AppPreference
    private lateinit var main_email: TextView
    private lateinit var buttonLogOut: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appPreference=AppPreference(this)
        main_email=findViewById(R.id.main_emailTV)
        buttonLogOut=findViewById(R.id.buttonLogOut)


        val userEmail=appPreference.getString("email","defaultValue")
        main_email.text=userEmail


        buttonLogOut.setOnClickListener {

            appPreference.editor.remove("email").apply()
            appPreference.editor.remove("password").apply()
            
            startActivity(Intent(this, LoginScreen::class.java))
            finish()
        }
    }
}