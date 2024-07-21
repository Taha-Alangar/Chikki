package com.tahaalangar.onlineshoppingapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tahaalangar.onlineshoppingapp.R
import com.tahaalangar.onlineshoppingapp.auth.AppPreference
import com.tahaalangar.onlineshoppingapp.auth.LoginScreen
import com.tahaalangar.onlineshoppingapp.databinding.FragmentHomeFragmentsBinding
import com.tahaalangar.onlineshoppingapp.databinding.FragmentProfileBinding
import com.tahaalangar.onlineshoppingapp.start.OnBoardingScreen


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var appPreference: AppPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentProfileBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appPreference = AppPreference(requireContext())

        // Set click listener for sign out layout
        binding.signOutLayout.setOnClickListener {
            // Clear SharedPreferences
            appPreference.clear()
            // Navigate to login screen
            navigateToLoginScreen()
        }

    }
    // Method to navigate to login screen
    private fun navigateToLoginScreen() {
        val intent = Intent(requireContext(), OnBoardingScreen::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        requireActivity().finish()
    }

}