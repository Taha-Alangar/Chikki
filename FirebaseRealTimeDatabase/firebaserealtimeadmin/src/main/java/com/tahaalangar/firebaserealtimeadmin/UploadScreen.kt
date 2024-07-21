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
import com.tahaalangar.firebaserealtimeadmin.databinding.ActivityUploadScreenBinding

class UploadScreen : AppCompatActivity() {
    private lateinit var binding: ActivityUploadScreenBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val ownerName=binding.ownerName.text.toString()
            val vehicleBrand=binding.vehicleBrand.text.toString()
            val vehicleRto=binding.vehicleRto.text.toString()
            val vehicleNumber=binding.vehicleNumber.text.toString()

            database= FirebaseDatabase.getInstance().getReference("Vehicles Information")
            val vehicleData=VehicleData(ownerName,vehicleBrand,vehicleRto,vehicleNumber)
            database.child(vehicleNumber).setValue(vehicleData).addOnSuccessListener {
                binding.ownerName.text.clear()
                binding.vehicleBrand.text.clear()
                binding.vehicleRto.text.clear()
                binding.vehicleNumber.text.clear()
                Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,MainActivity::class.java))
                finish()

            }.addOnFailureListener{

                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }


    }
}