package com.tahaalangar.bitestation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.tahaalangar.adapter.BuyAgainAdapter
import com.tahaalangar.bitestation.R
import com.tahaalangar.bitestation.databinding.FragmentHistoryBinding


class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var buyAgainAdapter: BuyAgainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentHistoryBinding.inflate(layoutInflater,container,false)
        setUpRecyclerView()
        return binding.root
    }

    private fun setUpRecyclerView(){
        val buyAgainFoodName= arrayListOf("food 1","food 2","food 3",)
        val buyAgainFoodPrice= arrayListOf("$10","$20","$30",)
        val buyAgainFoodImage= arrayListOf(R.drawable.menu_one,R.drawable.menu_four,R.drawable.menu_two,)
        buyAgainAdapter=BuyAgainAdapter(buyAgainFoodName,buyAgainFoodPrice,buyAgainFoodImage)
        binding.buyAgainRecyclerView.adapter=buyAgainAdapter
        binding.buyAgainRecyclerView.layoutManager=LinearLayoutManager(requireContext())

    }
    companion object {

    }
}