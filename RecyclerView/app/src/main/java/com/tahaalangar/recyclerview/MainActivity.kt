package com.tahaalangar.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.tahaalangar.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var rvAdapter: RvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvAdapter= RvAdapter(constant.getData(),this)
//        binding.rv.layoutManager=GridLayoutManager(this,2)
        binding.rv.layoutManager=LinearLayoutManager(this)
//        binding.rv.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        binding.rv.adapter=rvAdapter


    }

}