package com.tahaalangar.countriesapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CountryDetail : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)

        val img=intent.getIntExtra("img",R.drawable.india)
        val name=intent.getStringExtra("name")
        val des=intent.getStringExtra("des")

        findViewById<ImageView>(R.id.detailCountryImg).setImageResource(img)
        findViewById<TextView>(R.id.detailCountyName).text=name
        findViewById<TextView>(R.id.detailCountryDesciption).text=des
    }
}