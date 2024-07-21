package com.tahaalangar.bitestation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.tahaalangar.adapter.PopularAdapter
import com.tahaalangar.bitestation.MenuBottomSheetFragment
import com.tahaalangar.bitestation.R
import com.tahaalangar.bitestation.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding:FragmentHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(layoutInflater,container,false)
        binding.viewAllMenu.setOnClickListener {
            val bottomSheetDialog=MenuBottomSheetFragment()
            bottomSheetDialog.show(parentFragmentManager,"Test")
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageList = ArrayList<SlideModel>() // Create image list
        imageList.add(SlideModel(R.drawable.banner_one,ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.banner_two,ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.banner_three,ScaleTypes.FIT))

        val imageSlider=binding.imageSlider
        imageSlider.setImageList(imageList)
        imageSlider.setImageList(imageList,ScaleTypes.FIT)
        imageSlider.setItemClickListener(object : ItemClickListener {
            override fun onItemSelected(position: Int) {
                val itemPosition=imageList[position]
                val itemMessage="Selected image $position"
                Toast.makeText(requireContext(), itemMessage, Toast.LENGTH_SHORT).show()
                // You can listen here.
            }
            override fun doubleClick(position: Int) {
                // Do not use onItemSelected if you are using a double click listener at the same time.
                // Its just added for specific cases.
                // Listen for clicks under 250 milliseconds.
            } })

        val foodNames= listOf("Burger","Snadwich","Momos","item","Burger","Snadwich","Momos","item")
        val price= listOf("$5","$7","$6","$10","$5","$7","$6","$10")
        val popularFoodImages= listOf(R.drawable.menu_one,R.drawable.menu_five,R.drawable.menu_four,
            R.drawable.menu_two,R.drawable.menu_one,R.drawable.menu_five,R.drawable.menu_four,R.drawable.menu_two)
        val adapter=PopularAdapter(foodNames,price,popularFoodImages)
        binding.popularRecyclerView.layoutManager=LinearLayoutManager(requireContext())
        binding.popularRecyclerView.adapter=adapter



    }

    companion object {

    }
}