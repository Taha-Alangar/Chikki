package com.tahaalangar.firebaserealtimedatabase

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.tahaalangar.firebaserealtimedatabase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearch.setOnClickListener {
            val number = binding.edtSearch.text.toString()
            if (number.isNotEmpty()) {
                readData(number)
            } else {
                Toast.makeText(this, "Please Enter Vehicle Number", Toast.LENGTH_SHORT).show()

            }
        }
    }
    private fun readData(number:String){
        database= FirebaseDatabase.getInstance().getReference("Vehicles Information")
        database.child(number).get().addOnSuccessListener {
            if(it.exists()){
                val ownerName=it.child("ownerName").value
                val brand=it.child("vehicleBrand").value
                val rto=it.child("vehicleRTO").value
                Toast.makeText(this, "Result Found", Toast.LENGTH_SHORT).show()
                binding.edtSearch.text.clear()
                binding.name.text=ownerName.toString()
                binding.brand.text=brand.toString()
                binding.Rto.text=rto.toString()
            }
            else{
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }
    }
}