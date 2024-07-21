package com.tahaalangar.googleloginfirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tahaalangar.googleloginfirebase.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvRegisterSignIn.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
        binding.btnRegisterCreateAcc.setOnClickListener {
            val email = binding.edtRegisterEmail.text.toString()
            val password = binding.edtRegisterPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                MainActivity.auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            startActivity(Intent(this, SignInActivity::class.java))
                            finish()
                        }
                    }.addOnFailureListener {
                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show()
            }

        }

    }
}