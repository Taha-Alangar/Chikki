package com.tahaalangar.onlineshoppingapp.start

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.tahaalangar.onlineshoppingapp.R
import com.tahaalangar.onlineshoppingapp.adapters.IntroSlideAdapter
import com.tahaalangar.onlineshoppingapp.auth.AppPreference
import com.tahaalangar.onlineshoppingapp.auth.LoginScreen
import com.tahaalangar.onlineshoppingapp.pojos.IntroSlide

class OnBoardingScreen : AppCompatActivity() {
    private val introSliderAdapter= IntroSlideAdapter(
        listOf(
            IntroSlide("Find your next favourite books","Books Shelf is one the fast upcoming app, aiming to enrich the lives of millions of reader",R.drawable.onboarding_1),
            IntroSlide("Quick Daily Update","join as reader and look forward to the daily updated chapters which brings you an unequal reading atmosphere",R.drawable.onboarding_2),
            IntroSlide("Share your story and gets your fans","join the thousand aspiring authors start your journey telling your stories",R.drawable.onboarding_3)
        )
    )
    private lateinit var introSlideViewPager: ViewPager2
    private lateinit var indicatorContainer: LinearLayout
    private lateinit var nextIV: ImageView
    private lateinit var getStartedTv: TextView
    private lateinit var skipTv: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_screen)

        introSlideViewPager = findViewById(R.id.viewPager2)
        indicatorContainer = findViewById(R.id.indicatorContainer)
        nextIV = findViewById(R.id.next_Iv)
        getStartedTv = findViewById(R.id.getStartedTv)
        skipTv = findViewById(R.id.skip_TV)


        introSlideViewPager.adapter=introSliderAdapter

        setUpIndicators()
        setCurrentIndicator(1)


        introSlideViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
                if (position == introSliderAdapter.itemCount - 1) {
                    nextIV.visibility = ImageView.GONE
                    getStartedTv.visibility = TextView.VISIBLE
                    skipTv.visibility = TextView.GONE
                } else {
                    nextIV.visibility = ImageView.VISIBLE
                    getStartedTv.visibility = TextView.GONE
                    skipTv.visibility = TextView.VISIBLE
                }
            }
        })

        nextIV.setOnClickListener {
            if(introSlideViewPager.currentItem+1<introSliderAdapter.itemCount){
                introSlideViewPager.currentItem +=1

            }else{
                navigateToLoginScreen()
            }
        }
        getStartedTv.setOnClickListener {
            navigateToLoginScreen()
        }
        skipTv.setOnClickListener {
            navigateToLoginScreen()
        }
    }
    private fun navigateToLoginScreen() {
        val appPreference = AppPreference(this)
        appPreference.saveBoolean("is_first_launch", false)
        val intent = Intent(this, LoginScreen::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        finish()
    }
    private fun setUpIndicators(){
        val indicators= arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams:LinearLayout.LayoutParams=
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices){
            indicators[i]= ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams=layoutParams
            }
            indicatorContainer.addView(indicators[i])
        }
    }
    private fun setCurrentIndicator(index:Int){
        val childCount=indicatorContainer.childCount
        for (i in 0 until childCount){
            val imageView=indicatorContainer[i] as ImageView
            if (i==index){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            }else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }
}