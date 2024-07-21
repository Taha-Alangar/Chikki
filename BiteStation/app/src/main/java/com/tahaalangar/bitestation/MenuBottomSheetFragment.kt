package com.tahaalangar.bitestation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tahaalangar.adapter.MenuAdapter
import com.tahaalangar.bitestation.databinding.FragmentMenuBottomSheetBinding

class MenuBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentMenuBottomSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMenuBottomSheetBinding.inflate(inflater,container,false)

        binding.buttonBack.setOnClickListener {
            dismiss()
        }

        val menuFoodNames= mutableListOf("ththt","Snadwich","Momos","item","Burger","Snadwich","Momos","item")
        val menuPrice= mutableListOf("$5","$7","$6","$10","$5","$7","$6","$10")
        val menuFoodImages= mutableListOf(R.drawable.menu_one,R.drawable.menu_five,R.drawable.menu_four,
            R.drawable.menu_two,R.drawable.menu_one,R.drawable.menu_five,R.drawable.menu_four,R.drawable.menu_two)
        val adapter= MenuAdapter(menuFoodNames,menuPrice,menuFoodImages)
        binding.menuRecyclerView.layoutManager= LinearLayoutManager(requireContext())
        binding.menuRecyclerView.adapter=adapter

        return binding.root
    }

    companion object {
    }
}