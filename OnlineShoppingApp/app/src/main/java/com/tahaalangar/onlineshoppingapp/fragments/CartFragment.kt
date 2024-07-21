package com.tahaalangar.onlineshoppingapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tahaalangar.onlineshoppingapp.R
import com.tahaalangar.onlineshoppingapp.adapters.CartAdapter
import com.tahaalangar.onlineshoppingapp.databinding.FragmentCartBinding
import com.tahaalangar.onlineshoppingapp.databinding.FragmentHomeFragmentsBinding
import com.tahaalangar.onlineshoppingapp.pojos.AllCarts
import com.tahaalangar.onlineshoppingapp.pojos.CartPojo
import com.tahaalangar.onlineshoppingapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private lateinit var recyclerView_cart: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentCartBinding.inflate(layoutInflater,container,false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView_cart = binding.cartRecyclerView

        getCartList()


    }

    private fun getCartList() {

            RetrofitInstance.apiInterface.getAllCart().enqueue(object : Callback<CartPojo?> {
                override fun onResponse(call: Call<CartPojo?>, response: Response<CartPojo?>) {
                    if (response.code()==200 && response.body()!=null){
                        val linearLayoutManager= LinearLayoutManager(requireContext(),
                            LinearLayoutManager.VERTICAL, false)
                        recyclerView_cart.layoutManager=linearLayoutManager

                        val adapter= CartAdapter(response.body()!!,requireContext())
                        recyclerView_cart.adapter=adapter

                        Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<CartPojo?>, t: Throwable) {
                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                    Log.d("test",t.message.toString())
                }
            })
    }

}