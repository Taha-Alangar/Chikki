package com.tahaalangar.pharmacy

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MedicinePojo(
    @PrimaryKey(autoGenerate = true)
    val id:Long=0,
    val medicineNames:String,
    val manufacturer: String,
    val expiryDate: Long,
    val quantity: Int,
)
