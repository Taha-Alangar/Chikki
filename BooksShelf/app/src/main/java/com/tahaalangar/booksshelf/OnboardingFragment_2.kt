package com.tahaalangar.booksshelf

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class OnboardingFragment_2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_onboarding_2, container, false)
        val next2: TextView = view.findViewById(R.id.next2)
        next2.setOnClickListener {
            (activity as OnBoardingScreen).goToNextPage()
        }
        return view
    }

}