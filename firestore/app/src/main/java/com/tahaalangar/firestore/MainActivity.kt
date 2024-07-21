package com.tahaalangar.firestore

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {
    private val db = Firebase.firestore
    private lateinit var auth: FirebaseAuth
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        if (currentUser == null) {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
        val edtName=findViewById<EditText>(R.id.edt_Name)
        val edtEmail=findViewById<EditText>(R.id.edt_Email)
        val btnSaveUser=findViewById<Button>(R.id.btn_save_User)

        val btnFetch=findViewById<Button>(R.id.btn_fetch)
        btnFetch.setOnClickListener {
            startActivity(Intent(this, FetchActivity::class.java))
        }
        btnSaveUser.setOnClickListener {
            val name = edtName.text.toString()
            val email = edtEmail.text.toString()

            if (currentUser != null) {
                val userId = currentUser.uid
                val user = Users(name, email)
                db.collection("users").document(userId).set(user)
                    .addOnSuccessListener {
                        Toast.makeText(this, "User added successfully", Toast.LENGTH_SHORT).show()
                        edtName.text.clear()
                        edtEmail.text.clear()
                    }.addOnFailureListener {
                        Toast.makeText(this, "Failed to add user", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "User not authenticated", Toast.LENGTH_SHORT).show()
            }
        }

    }
}