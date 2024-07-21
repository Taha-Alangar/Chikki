package com.tahaalangar.firebaserealtimeadmin

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tahaalangar.firebaserealtimeadmin.databinding.ActivityMainBinding
import com.tahaalangar.firebaserealtimeadmin.databinding.ActivityUploadScreenBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnUpload.setOnClickListener {
            startActivity(Intent(this, UploadScreen::class.java))
            finish()
        }
        binding.btnDelete.setOnClickListener {
            startActivity(Intent(this, DeleteScreen::class.java))
            finish()
        }
        binding.btnUploadCar.setOnClickListener {
            startActivity(Intent(this, UploadCar::class.java))
            finish()
        }
    }
}