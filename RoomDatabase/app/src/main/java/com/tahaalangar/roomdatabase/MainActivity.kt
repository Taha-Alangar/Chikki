package com.tahaalangar.roomdatabase

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(),AdpaterListener {
    private lateinit var fruitsName:EditText
    private lateinit var fruitsWeight:EditText
    private lateinit var fruitsPrice:EditText


    private lateinit var searchButton:Button

    private lateinit var insertButton:Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RDAdapter


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fruitsName=findViewById(R.id.editTextFruitsName)
        fruitsWeight=findViewById(R.id.editTextFruitsWeight)
        fruitsPrice=findViewById(R.id.editTextFruitsPrice)


        searchButton=findViewById(R.id.searchBtn)

        insertButton=findViewById(R.id.buttonInsert)
        recyclerView=findViewById(R.id.mainRecyclerView)


        searchButton.setOnClickListener {
            val intent=Intent(this,SearchScreen::class.java)
            startActivity(intent)
        }
        insertButton.setOnClickListener {
            val str_name=fruitsName.text.toString()
            val str_weight=fruitsWeight.text.toString()
            val str_price=fruitsPrice.text.toString()

            if (str_name.isNotEmpty() && str_weight.isNotEmpty()&& str_price.isNotEmpty()){
                val user=Contact(Math.random().toLong(),str_name,str_weight,str_price)
                adapter.clearData()
                adapter.notifyDataSetChanged()      // refresh adapter data


                lifecycleScope.launch {
                    AppDatabase.getDatabase(this@MainActivity).userDao().insert(user)
                    runOnUiThread {

                    fruitsName.text.clear()
                    fruitsWeight.text.clear()
                    fruitsPrice.text.clear()
                }
                }

            }else{
                Toast.makeText(this, "Enter Something", Toast.LENGTH_SHORT).show()
            }
        }

        adapter=RDAdapter(this,this)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter=adapter

    }

    companion object {
        private const val REQUEST_CODE_SELECT_IMAGE = 100
    }
    fun fetchData() {
        AppDatabase.getDatabase(this).userDao().getAllUsers().observe(this) { userList ->
            adapter.clearData()
            adapter.addAll(userList)
            adapter.notifyDataSetChanged()
        }
    }


    override fun onResume() {
        super.onResume()
        fetchData()
    }

    override fun onUpdate(pojo: Contact) {
        val intent=Intent(this@MainActivity,UpdateScreen::class.java)
        intent.putExtra("model",pojo)
        startActivity(intent)
    }

    override fun onDelete(pojo: Contact) {
        lifecycleScope.launch {
            AppDatabase.getDatabase(this@MainActivity).userDao().delete(pojo)
            runOnUiThread {
                Toast.makeText(this@MainActivity, "Deleted Successfully", Toast.LENGTH_SHORT).show()
                fetchData()
            }
        }
    }
}
