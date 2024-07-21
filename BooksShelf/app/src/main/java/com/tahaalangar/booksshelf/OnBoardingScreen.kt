package com.tahaalangar.booksshelf

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2

class OnBoardingScreen : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_screen)

        viewPager = findViewById(R.id.viewPager)
        val fragments = listOf(OnboardingFragment_1(), OnboardingFragment_2(), OnboardingFragment_3())
        val adapter = OnboardingPagerAdapter(fragments, supportFragmentManager,lifecycle)
        viewPager.adapter = adapter
    }

    fun goToNextPage() {
        if (viewPager.currentItem < (viewPager.adapter?.itemCount?.minus(1) ?: 0)) {
            viewPager.currentItem += 1
        }
    }

    fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}