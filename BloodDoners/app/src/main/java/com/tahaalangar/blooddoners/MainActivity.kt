package com.tahaalangar.blooddoners

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.bloodDonnerRecyclerView)
        getBloodDonnerReport()
    }
private fun getBloodDonnerReport(){
    RetrofitInstance.apiInterface.getData().enqueue(object : Callback<BloodDoners?> {
        override fun onResponse(call: Call<BloodDoners?>, response: Response<BloodDoners?>) {
            if (response.code()==200 && response.body()!=null){
                val linearLayoutManager=LinearLayoutManager(this@MainActivity)
                recyclerView.layoutManager=linearLayoutManager

                val adapter=BloodDonnerAdapter(response.body()!!,this@MainActivity)
                recyclerView.adapter=adapter

                Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()

            }
            else{
                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onFailure(call: Call<BloodDoners?>, t: Throwable) {
            Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            Log.d("test",t.message.toString())
        }
    })

}
}