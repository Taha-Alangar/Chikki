package com.trycatchprojects.maganlalchikki

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trycatchprojects.maganlalchikki.databinding.FragmentCheckOutBinding


class CheckOutFragment : Fragment() {
    private lateinit var binding:FragmentCheckOutBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding= FragmentCheckOutBinding.inflate(layoutInflater)



        return binding.root
    }
}