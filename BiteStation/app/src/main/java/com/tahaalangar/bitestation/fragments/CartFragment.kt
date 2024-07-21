package com.tahaalangar.bitestation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.tahaalangar.adapter.CartAdapter
import com.tahaalangar.bitestation.R
import com.tahaalangar.bitestation.databinding.FragmentCartBinding

class CartFragment : Fragment() {
private lateinit var  binding:FragmentCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentCartBinding.inflate(layoutInflater,container,false)

        val foodNames= mutableListOf("ththt","Snadwich","Momos","item","Burger","Snadwich","Momos","item")
        val price= mutableListOf("$5","$7","$6","$10","$5","$7","$6","$10")
        val popularFoodImages= mutableListOf(R.drawable.menu_one,R.drawable.menu_five,R.drawable.menu_four,
            R.drawable.menu_two,R.drawable.menu_one,R.drawable.menu_five,R.drawable.menu_four,R.drawable.menu_two)
        val adapter= CartAdapter(foodNames,price,popularFoodImages)
        binding.cartRecyclerView.layoutManager= LinearLayoutManager(requireContext())
        binding.cartRecyclerView.adapter=adapter
        return binding.root

    }

    companion object {

    }
}