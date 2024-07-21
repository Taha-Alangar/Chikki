package com.tahaalangar.shoesshopping

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var signUpBtn:Button
    private lateinit var signInBtn:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        signInBtn=findViewById(R.id.signIn_tv)
        signUpBtn=findViewById(R.id.signUp_btn)

        signUpBtn.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
        }
        signInBtn.setOnClickListener {
            startActivity(Intent(this,SignInActivity::class.java))
        }
    }
}