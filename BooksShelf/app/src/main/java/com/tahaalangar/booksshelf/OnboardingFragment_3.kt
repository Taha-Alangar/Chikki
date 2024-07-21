package com.tahaalangar.booksshelf

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class OnboardingFragment_3 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_onboarding_3, container, false)
        val finish: TextView = view.findViewById(R.id.finish)
        finish.setOnClickListener {
            (activity as OnBoardingScreen).navigateToLogin()
        }
        return view
    }
}