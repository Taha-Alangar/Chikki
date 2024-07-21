package com.tahaalangar.booksshelf

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class OnboardingFragment_1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_onboarding_1, container, false)
        val next1: TextView = view.findViewById(R.id.next1)
        next1.setOnClickListener {
            (activity as OnBoardingScreen).goToNextPage()
        }
        return view
    }

}