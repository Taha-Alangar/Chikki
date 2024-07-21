package com.tahaalangar.firebaserealtimeadmin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.tahaalangar.firebaserealtimeadmin.databinding.ActivityUploadCarBinding

class UploadCar : AppCompatActivity() {
    private lateinit var binding: ActivityUploadCarBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUploadCarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSave.setOnClickListener {
            val name=binding.ownerName.text.toString()
            val brand=binding.vehicleBrand.text.toString()
            val Rto=binding.vehicleRto.text.toString()
            val number=binding.vehicleNumber.text.toString()
            if(name.isEmpty()||brand.isEmpty()||Rto.isEmpty()||number.isEmpty()){
                Toast.makeText(this,"Please enter all fields", Toast.LENGTH_SHORT).show()
            }else{
                uploadCar(name,brand,Rto,number)
            }
        }

    }
    private fun uploadCar(name:String,brand:String,Rto:String,number:String) {
        database = FirebaseDatabase.getInstance().getReference("cars")
        val carData=VehicleData(name,brand,Rto,number)
        database.child(name).setValue(carData).addOnSuccessListener {
            binding.ownerName.text.clear()
            binding.vehicleBrand.text.clear()
            binding.vehicleRto.text.clear()
            binding.vehicleNumber.text.clear()
            Toast.makeText(this,"Data inserted successfully", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,MainActivity::class.java))
            finish()

        }.addOnFailureListener{
            Toast.makeText(this,"Data not inserted", Toast.LENGTH_SHORT).show()
        }

    }
}