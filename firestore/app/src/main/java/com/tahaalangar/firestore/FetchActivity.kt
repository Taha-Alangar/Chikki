package com.tahaalangar.firestore

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class FetchActivity : AppCompatActivity() {
    private var db= Firebase.firestore
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch)

        val fetcheName=findViewById<TextView>(R.id.fetchName)
        val fetcheEmail=findViewById<TextView>(R.id.fetchEmail)

        val userId=FirebaseAuth.getInstance().currentUser!!.uid
        val ref=db.collection("users").document(userId)
        ref.get().addOnSuccessListener {
            fetcheName.text=it.get("name").toString()
            fetcheEmail.text=it.get("email").toString()
        }.addOnFailureListener{
            fetcheName.text="Failed"
            fetcheEmail.text="Failed"
        }
        


    }
}