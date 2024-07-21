package com.tahaalangar.onlineshoppingapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.tahaalangar.onlineshoppingapp.R
import com.tahaalangar.onlineshoppingapp.databinding.FragmentCartBinding
import com.tahaalangar.onlineshoppingapp.databinding.FragmentDetailBinding
import com.tahaalangar.onlineshoppingapp.pojos.Product


class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private var product: Product? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentDetailBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        product= arguments?.getSerializable("product") as Product?
        product?.let { displayProductDetails(it) }

    }
    private fun displayProductDetails(product: Product) {
        binding.textView16.text = product.title
        binding.textView17.text = "$ ${product.price}"
        binding.textView27.text = product.rating.toString()
        binding.textView30.text = product.description
        Picasso.get().load(product.thumbnail).into(binding.imageView15)
        // You can add more fields to display here
        // For example:

        // ... and so on
    }

}