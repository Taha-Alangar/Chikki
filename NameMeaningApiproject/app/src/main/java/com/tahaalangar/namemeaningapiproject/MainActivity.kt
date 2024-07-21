package com.tahaalangar.namemeaningapiproject

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val male:ImageView=findViewById(R.id.maleImage)
        val feMale:ImageView=findViewById(R.id.femaleImage)

        male.setOnClickListener {

            val intent=Intent(this,NameSynonyms::class.java)
            intent.putExtra("gender","male")
            startActivity(intent)
        }
        feMale.setOnClickListener {
            val intent=Intent(this,NameSynonyms::class.java)
            intent.putExtra("gender","female")
            startActivity(intent)

        }


    }
}