package com.tahaalangar.googleloginfirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.tahaalangar.googleloginfirebase.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val gso=GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(getString(R.string.client_id))
        .requestEmail()
        .build()
        val googleSignInClient=GoogleSignIn.getClient(this,gso)

        binding.tvSignInRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))

        }
        binding.btnSignIn.setOnClickListener {
            val email = binding.edtSignInEmail.text.toString()
            val password = binding.edtSignInPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                MainActivity.auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }
                    }.addOnFailureListener {
                        Toast.makeText(this, it.localizedMessage, Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show()
            }

        }

        binding.googleLogin.setOnClickListener{
            googleSignInClient.signOut()
            startActivityForResult(googleSignInClient.signInIntent,13)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 13){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Toast.makeText(this, "Google sign in failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {

        val credentails=GoogleAuthProvider.getCredential(idToken,null)
        MainActivity.auth.signInWithCredential(credentails)
            .addOnCompleteListener (this){task->
                if (task.isSuccessful){
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                }
            }.addOnFailureListener {
                Toast.makeText(this,it.message.toString(),Toast.LENGTH_SHORT).show()

            }
    }


}