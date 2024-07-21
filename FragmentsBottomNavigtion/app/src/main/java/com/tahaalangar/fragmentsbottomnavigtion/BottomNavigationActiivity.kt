package com.tahaalangar.fragmentsbottomnavigtion

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationActiivity : AppCompatActivity() {
    private lateinit var bottomNavigationView:BottomNavigationView
    private lateinit var fragmentManager: FragmentManager


    private var currentFragmentTag: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation_actiivity)

        bottomNavigationView=findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener {menuItem->
            when(menuItem.itemId){
                R.id.bottom_home->{
                    replaceFragments(HomeFragment(), "HOME_FRAGMENT")
                    true
                }
                R.id.bottom_search->{
                    replaceFragments(SearchFragment(), "SEARCH_FRAGMENT")
                    true
                }
                R.id.bottom_addBox->{
                    replaceFragments(AddBoxFragment(), "ADD_BOX_FRAGMENT")
                    true
                }
                R.id.bottom_news->{
                    replaceFragments(NewsFragment(), "NEWS_FRAGMENT")
                    true
                }
                R.id.bottom_profile->{
                    replaceFragments(ProfileFragment(), "PROFILE_FRAGMENT")
                    true
                }
                else -> {
                    false
                }
            }
        }
        replaceFragments(HomeFragment(), "HOME_FRAGMENT")
    }
    private fun replaceFragments(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment, tag).commit()
        currentFragmentTag = tag
    }

    override fun onBackPressed() {
        if (currentFragmentTag != "HOME_FRAGMENT") {
            replaceFragments(HomeFragment(), "HOME_FRAGMENT")
            bottomNavigationView.selectedItemId=R.id.bottom_home
        } else {
            super.onBackPressed()
        }


    }
}
// just like the intent put extra here in
// fragments we will get arguments search about it