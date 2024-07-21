package com.tahaalangar.roomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user_table")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id:Long=0,
    val name:String,
    val weight: String,
    val price: String,
):Serializable



//serializable means here above i am passing three things like  whole object so for passing all this three from one
// screen to another i have to user serializable for that just like intent put and get methods andi want to
//pass single data i will not use serializable for that