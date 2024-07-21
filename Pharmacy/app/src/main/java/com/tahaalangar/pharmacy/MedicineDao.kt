package com.tahaalangar.pharmacy

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface MedicineDao {

    @Query("SELECT * FROM medicinepojo")
    fun getAllMedicines(): LiveData<List<MedicinePojo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(medicine: MedicinePojo)

    @Update
    suspend fun update(medicine: MedicinePojo)

    @Delete
    suspend fun delete(medicine: MedicinePojo)
}