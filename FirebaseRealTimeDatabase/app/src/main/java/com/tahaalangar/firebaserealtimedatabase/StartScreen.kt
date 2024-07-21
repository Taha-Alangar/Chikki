package com.tahaalangar.firebaserealtimedatabase

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class StartScreen : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_screen)
        val mainButton=findViewById<Button>(R.id.btnMain)
        val RVButton=findViewById<Button>(R.id.btnRV)
        mainButton.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
        RVButton.setOnClickListener {
            startActivity(Intent(this,RecyclerViewScree::class.java))
        }
    }
}