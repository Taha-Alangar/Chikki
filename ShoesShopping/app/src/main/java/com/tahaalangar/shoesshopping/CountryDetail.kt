package com.tahaalangar.shoesshopping

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CountryDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)

        val shoeImage = intent.getIntExtra("SHOE_IMAGE",R.drawable.third_shoes)
        val brandName = intent.getStringExtra("BRAND_NAME")
        val category = intent.getStringExtra("CATEGORY")
        val price = intent.getIntExtra("PRICE", 0)

        findViewById<ImageView>(R.id.shoesImage).setImageResource(shoeImage)
        findViewById<TextView>(R.id.brandName).text = brandName
        findViewById<TextView>(R.id.categoryName).text = category
        findViewById<TextView>(R.id.shoesPrice).text = price.toString()
    }
}