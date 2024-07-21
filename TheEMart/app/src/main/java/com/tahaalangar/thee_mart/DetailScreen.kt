package com.tahaalangar.thee_mart

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.squareup.picasso.Picasso
import com.tahaalangar.thee_mart.R

class DetailScreen : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_screen)

        val imagess=findViewById<ImageView>(R.id.detailScreenThumbnail)

        val title=intent.getStringExtra("title")
        val brand=intent.getStringExtra("brand")
        val discount=intent.getDoubleExtra("discount",0.0)
        val price=intent.getIntExtra("price",0)
        val stock=intent.getIntExtra("stocks",0)
        val thumbnail=intent.getStringExtra("thumbnail")

        findViewById<TextView>(R.id.detailScreenTitle).text=title
        findViewById<TextView>(R.id.detailScreenBrand).text=brand
        findViewById<TextView>(R.id.detailScreenDiscount).text="$discount% OFF"
        findViewById<TextView>(R.id.detailScreePrice).text="$ $price"
        findViewById<TextView>(R.id.detailScreenStock).text="Stocks Available $stock"
        Picasso.get().load(thumbnail).into(imagess);



    }
}