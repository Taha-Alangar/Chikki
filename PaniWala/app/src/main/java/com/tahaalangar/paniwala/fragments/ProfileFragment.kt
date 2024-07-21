package com.tahaalangar.paniwala.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tahaalangar.paniwala.LoginActivity
import com.tahaalangar.paniwala.R
import com.tahaalangar.paniwala.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding:FragmentProfileBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var googleSignInClient: GoogleSignInClient
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding=FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        // Configure Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            // Add more options if needed
            .build()

        // Initialize GoogleSignInClient
        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)


        binding.signOut.setOnClickListener {
            signOut()
        }
        binding.manageAddress.setOnClickListener {

        }
        binding.myOrders.setOnClickListener {

        }
    }

    private fun signOut() {
        // Sign out from Firebase Authentication
        auth.signOut()

        // Sign out from Google Sign-In (if applicable)
        googleSignInClient.signOut().addOnCompleteListener(requireActivity()) {
            // Navigate to Login Activity
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            requireActivity().finish()  // Finish current activity to prevent going back
        }
    }
}