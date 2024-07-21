package com.tahaalangar.booksshelf

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
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

class IntroScreens : AppCompatActivity() {
    private val introSliderAdapter=IntroSlideAdapter(
        listOf(
            IntroSlide("Find your next favourite books","Books Shelf is one the fast upcoming app, aiming to enrich the lives of millions of reader",R.drawable.onboarding_1),
            IntroSlide("Quick Daily Update","join as reader and look forward to the daily updated chapters which brings you an unequal reading atmosphere",R.drawable.onboarding_2),
            IntroSlide("Share your story and gets your fans","join the thousand aspiring authors start your journey telling your stories",R.drawable.onboarding_3)
        )
    )
    private lateinit var introSlideViewPager:ViewPager2
    private lateinit var indicatorContainer:LinearLayout
    private lateinit var nextTV:TextView
    private lateinit var skipTv:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_screens)

        introSlideViewPager=findViewById(R.id.viewPager2)
        indicatorContainer=findViewById(R.id.indicatorContainer)
        nextTV=findViewById(R.id.nextTV)
        skipTv=findViewById(R.id.skipTV)

        introSlideViewPager.adapter=introSliderAdapter

        setUpIndicators()
        setCurrentIndicator(1)

        introSlideViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
                // Check if the user is on the third page
                if (position == 2) { // Indexing starts from 0, so 2 represents the third page
                    nextTV.text = "Login"// Change text to "Login"
                    skipTv.text="Attendence"
                    skipTv.setOnClickListener {
                        Intent(applicationContext,AttendenceScreen::class.java).also {
                            startActivity(it)
                        }
                    }
                } else {
                    nextTV.text = "Next" // Change text back to "Next" for other pages

                    skipTv.text="skip"
                }
            }
        })
        nextTV.setOnClickListener {
            if(introSlideViewPager.currentItem+1<introSliderAdapter.itemCount){
                introSlideViewPager.currentItem +=1

            }else{
                Intent(applicationContext,LoginScreen::class.java).also {
                    startActivity(it)
                }
            }
        }
        skipTv.setOnClickListener {
            Intent(applicationContext,LoginScreen::class.java).also {
                startActivity(it)
            }
        }
    }
    private fun setUpIndicators(){
        val indicators= arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams:LinearLayout.LayoutParams=
            LinearLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices){
            indicators[i]=ImageView(applicationContext)
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