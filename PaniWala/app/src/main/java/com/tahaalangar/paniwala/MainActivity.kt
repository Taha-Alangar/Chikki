package com.tahaalangar.paniwala

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tahaalangar.paniwala.adapter.ProductsAdapter
import com.tahaalangar.paniwala.databinding.ActivityMainBinding
import com.tahaalangar.paniwala.fragments.CartFragment
import com.tahaalangar.paniwala.fragments.HomeFragment
import com.tahaalangar.paniwala.fragments.MyOrdersFragment
import com.tahaalangar.paniwala.fragments.ProfileFragment
import com.tahaalangar.paniwala.fragments.SearchFragment
import com.tahaalangar.paniwala.pojo.Products

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var currentFragmentTag: String? = null

    companion object {
        private const val HOME_FRAGMENT_TAG = "HOME_FRAGMENT"
        private const val SEARCH_FRAGMENT_TAG = "SEARCH_FRAGMENT"
        private const val CART_FRAGMENT_TAG = "CART_FRAGMENT"
        private const val ORDERS_FRAGMENT_TAG = "ORDERS_FRAGMENT"
        private const val PROFILE_FRAGMENT_TAG = "PROFILE_FRAGMENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupBottomNavigation()
        if (savedInstanceState == null) {
            // Load the default fragment on initial creation
            replaceFragment(HomeFragment(), HOME_FRAGMENT_TAG)
        }


    }
    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment(), HOME_FRAGMENT_TAG)
                    true
                }
                R.id.search -> {
                    replaceFragment(SearchFragment(), SEARCH_FRAGMENT_TAG)
                    true
                }
                R.id.cart -> {
                    replaceFragment(CartFragment(), CART_FRAGMENT_TAG)
                    true
                }
                R.id.orders -> {
                    replaceFragment(MyOrdersFragment(), ORDERS_FRAGMENT_TAG)
                    true
                }
                R.id.profile -> {
                replaceFragment(ProfileFragment(), PROFILE_FRAGMENT_TAG)
                true
            }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment, tag)
            .commit()
        currentFragmentTag = tag
    }

    override fun onBackPressed() {
        if (currentFragmentTag != HOME_FRAGMENT_TAG) {
            // If the current fragment is not the home fragment, replace it with the home fragment
            replaceFragment(HomeFragment(), HOME_FRAGMENT_TAG)
            binding.bottomNavigation.selectedItemId = R.id.home
        } else {
            // If the current fragment is the home fragment, use the default back press behavior
            super.onBackPressed()
        }
    }
}