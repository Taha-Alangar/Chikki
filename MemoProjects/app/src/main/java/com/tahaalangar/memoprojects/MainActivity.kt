package com.tahaalangar.memoprojects

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.tahaalangar.memoprojects.databinding.ActivityMainBinding
import com.tahaalangar.memoprojects.fragments.CartFragment
import com.tahaalangar.memoprojects.fragments.HomeFragment
import com.tahaalangar.memoprojects.fragments.ProfileFragment
import com.tahaalangar.memoprojects.fragments.ProjectsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var currentFragmentTag: String? = null

        companion object {
        private const val HOME_FRAGMENT_TAG = "HOME_FRAGMENT"
        private const val PROJECTS_FRAGMENT_TAG = "PROJECTS_FRAGMENT"
        private const val CART_FRAGMENT_TAG = "CART_FRAGMENT"
        private const val PROFILE_FRAGMENT_TAG = "PROFILE_FRAGMENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigation()
        if (savedInstanceState == null) {
            // Load the default fragment on initial creation
            replaceFragment(HomeFragment(), HOME_FRAGMENT_TAG,false)
        }
    }
    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.bottom_home -> {
                    replaceFragment(HomeFragment(), HOME_FRAGMENT_TAG,false)
                    true
                }
                R.id.bottomProjects -> {
                    replaceFragment(ProjectsFragment(), PROJECTS_FRAGMENT_TAG,false)
                    true
                }
                R.id.bottom_cart -> {
                    replaceFragment(CartFragment(), CART_FRAGMENT_TAG,false)
                    true
                }

                R.id.bottom_profile -> {
                    replaceFragment(ProfileFragment(), PROFILE_FRAGMENT_TAG,false)
                    true
                }
                else -> false
            }
        }
    }
    private fun replaceFragment(fragment: Fragment, tag: String, addToBackStack: Boolean) {
        val fragmentInManager = supportFragmentManager.findFragmentByTag(tag)
        if (fragmentInManager != null && fragmentInManager.isVisible) {
            return
        }
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment, tag)
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
        currentFragmentTag = tag
    }
    override fun onBackPressed() {
        if (currentFragmentTag != HOME_FRAGMENT_TAG) {
            replaceFragment(HomeFragment(), HOME_FRAGMENT_TAG, false)
            binding.bottomNavigation.selectedItemId = R.id.bottom_home
        } else {
            super.onBackPressed()
        }
    }
}