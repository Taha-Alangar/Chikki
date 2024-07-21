package com.tahaalangar.shoesshopping

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvAdapter: ShoesRvAdapter
    private lateinit var recyclerView: RecyclerView // profit of late init is we can use it globally in whole class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView=findViewById(R.id.recyclerView)

        rvAdapter= ShoesRvAdapter(Constant.myDataList,this)
        recyclerView.layoutManager=GridLayoutManager(this,2)
        recyclerView.adapter=rvAdapter

    }


}