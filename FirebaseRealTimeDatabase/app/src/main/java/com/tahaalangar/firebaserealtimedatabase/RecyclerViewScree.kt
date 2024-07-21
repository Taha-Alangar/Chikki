package com.tahaalangar.firebaserealtimedatabase

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.tahaalangar.firebaserealtimedatabase.databinding.ActivityRecyclerViewScreeBinding

class RecyclerViewScree : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewScreeBinding
    private lateinit var dataRef: DatabaseReference
    private lateinit var vehicleList: ArrayList<VehicleInfor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewScreeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.Rv.layoutManager = LinearLayoutManager(this)

        vehicleList= arrayListOf()

        dataRef=FirebaseDatabase.getInstance().getReference("Vehicles Information")
        dataRef.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (vehicleSnapshot in snapshot.children){
                        val vehicle=vehicleSnapshot.getValue(VehicleInfor::class.java)
                        if (!vehicleList.contains(vehicle)){

                            vehicleList.add(vehicle!!)
                        }
                    }
                    binding.Rv.adapter=RVApater(vehicleList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@RecyclerViewScree, error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}