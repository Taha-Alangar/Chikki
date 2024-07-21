package com.tahaalangar.tablayout

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tahaalangar.tablayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter=viewPagerAdapter(supportFragmentManager)

        adapter.addFragment(bookFragment(),"Books")
        adapter.addFragment(PenFragment(),"Pen")
        adapter.addFragment(laptopFragment(),"Laptop")

        binding.viewPager.adapter=adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }
}