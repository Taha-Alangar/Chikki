package com.tahaalangar.bitestation

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tahaalangar.bitestation.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private val binding:ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.signupBtn.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
        binding.loginTv.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
}