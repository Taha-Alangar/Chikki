package com.tahaalangar.loginsharedpreference

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginScreen : AppCompatActivity() {
    private lateinit var appPreference: AppPreference
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        appPreference=AppPreference(this)
        email=findViewById(R.id.editTextEmailAddress)
        password=findViewById(R.id.editTextPassword)
        button=findViewById(R.id.buttonLogin)

        button.setOnClickListener {

            val nn=appPreference.saveString("email",email.text.toString())
            appPreference.saveString("password",password.text.toString())

            if (email.text.isNotBlank() && password.text.isNotBlank()){
                if(email.text.toString().trim() == "taha" && password.text.toString().trim() == "123"){
                    val intent=Intent(this,MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Invalid Credentails", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Enter something", Toast.LENGTH_SHORT).show()
            }
        }
    }
}