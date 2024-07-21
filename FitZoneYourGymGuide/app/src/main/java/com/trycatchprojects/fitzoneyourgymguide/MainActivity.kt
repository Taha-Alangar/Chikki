package com.trycatchprojects.fitzoneyourgymguide

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.trycatchprojects.fitzoneyourgymguide.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    var actionBarToggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        // Initialize ActionBarDrawerToggle
        actionBarToggle = ActionBarDrawerToggle(this, binding.drawerLayout,binding.toolbar, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(actionBarToggle!!)
        actionBarToggle!!.syncState()

        // Check if supportActionBar is not null
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationIcon(R.drawable.icon_menu)


        // Set NavigationItemSelectedListener
        binding.navigationView.setNavigationItemSelectedListener(this)

        // Setup NavController with Bottom Navigation View
        val navController = findNavController(R.id.fragment)
        NavigationUI.setupWithNavController(binding.bottomNavView, navController)


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> {
               findNavController(R.id.fragment).navigate(R.id.shareFragment)
            }
            R.id.rateUs -> {
                findNavController(R.id.fragment).navigate(R.id.rateUsFragment)
            }
            R.id.privacyPolicy -> {
                findNavController(R.id.fragment).navigate(R.id.privacyPolicyFragment)
            }
        }
        binding.drawerLayout.closeDrawers()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarToggle!!.onOptionsItemSelected(item)) {
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}
