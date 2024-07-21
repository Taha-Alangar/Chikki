package com.tahaalangar.sharedprefernceproject

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    private lateinit var preference:AppPreference
    private lateinit var email:EditText
    private lateinit var password:EditText
    private lateinit var button:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        preference= AppPreference(this)

        email=findViewById(R.id.editTextEmail)
        password=findViewById(R.id.editTextPassword)

        button.setOnClickListener {

            preference.saveString("email",email.text.toString())
            preference.saveString("password",password.text.toString())

            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)




//            Toast.makeText(this, "Login successfully", Toast.LENGTH_SHORT).show()
//
//            email.setText("")
//            password.setText("")
        }

    }
}