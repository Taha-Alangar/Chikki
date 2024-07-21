package com.tahaalangar.onlineshoppingapp.screens

import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tahaalangar.onlineshoppingapp.R
import com.tahaalangar.onlineshoppingapp.adapters.CategoryList_Apater
import com.tahaalangar.onlineshoppingapp.fragments.CartFragment
import com.tahaalangar.onlineshoppingapp.fragments.CategoryFragment
import com.tahaalangar.onlineshoppingapp.fragments.HomeFragment
import com.tahaalangar.onlineshoppingapp.fragments.MyOrderFragment
import com.tahaalangar.onlineshoppingapp.fragments.OnCategorySelectedListener
import com.tahaalangar.onlineshoppingapp.fragments.ProfileFragment
import com.tahaalangar.onlineshoppingapp.fragments.SearchFragments
import com.tahaalangar.onlineshoppingapp.fragments.WhishlistFragment
import com.tahaalangar.onlineshoppingapp.pojos.CategoryList
import com.tahaalangar.onlineshoppingapp.pojos.CategoryListImages
import com.tahaalangar.onlineshoppingapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), OnCategorySelectedListener{

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var fragmentManager: FragmentManager
    private var currentFragmentTag: String? = null
    private lateinit var rootView: View

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView=findViewById(R.id.bottom_navigation)
        rootView = findViewById(android.R.id.content)
        bottomNavigationView.setOnItemSelectedListener {menuItem->
            when(menuItem.itemId){
                R.id.bottom_home->{
                    replaceFragments(HomeFragment(), "HOME_FRAGMENT")
                    true
                }
                R.id.bottom_cart->{
                    replaceFragments(CartFragment(), "SEARCH_FRAGMENT")
                    true
                }
                R.id.bottom_whishlist->{
                    replaceFragments(WhishlistFragment(), "ADD_BOX_FRAGMENT")
                    true
                }
                R.id.bottom_myOrder->{
                    replaceFragments(MyOrderFragment(), "NEWS_FRAGMENT")
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
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment, tag)
            .commit()
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
    override fun onCategorySelected(category: String) {
        val fragment = CategoryFragment()
        val bundle = Bundle()
        bundle.putString("category", category)
        fragment.arguments = bundle
        replaceFragments(fragment, "CATEGORY_FRAGMENT")
    }


    fun navigateToSearchFragment(query: String) {
        val fragment = SearchFragments()
        val bundle = Bundle()
        bundle.putString("query", query)
        fragment.arguments = bundle
        replaceFragments(fragment, "SEARCH_FRAGMENT")
    }
}




