package com.tahaalangar.bitestation

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tahaalangar.bitestation.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : AppCompatActivity() {
    private val binding:ActivityOnBoardingBinding by lazy {
            ActivityOnBoardingBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.nextButton.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
        }
}