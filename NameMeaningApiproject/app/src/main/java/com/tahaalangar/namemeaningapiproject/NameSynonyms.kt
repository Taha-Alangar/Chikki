package com.tahaalangar.namemeaningapiproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NameSynonyms : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name_synonyms)

        recyclerView = findViewById(R.id.synonymsRecyclerView)

        val gender = intent.getStringExtra("gender")

        val category = 8
        val genderValue = if (gender == "male") 1 else 2 // 1 for male, 2 for female

        getData(category, genderValue)

    }

     private fun getData(category:Int, gender:Int){
        RetrofitInstance.apiInterface.getData(category,gender).enqueue(object : Callback<category_and_gender?> {
            override fun onResponse(
                call: Call<category_and_gender?>,
                response: Response<category_and_gender?>
            ) {
                if (response.code()==200 && response.body()!=null){
                    val linearLayoutManager=LinearLayoutManager(this@NameSynonyms)
                    recyclerView.layoutManager=linearLayoutManager

                    val adapter=SynonymsAdapter(response.body()!!,this@NameSynonyms)
                    recyclerView.adapter=adapter

                    Toast.makeText(this@NameSynonyms, "Success", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this@NameSynonyms, "Error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<category_and_gender?>, t: Throwable) {
                Toast.makeText(this@NameSynonyms, t.message, Toast.LENGTH_SHORT).show()
                Log.d("test",t.message.toString())
            }
        })
    }

}