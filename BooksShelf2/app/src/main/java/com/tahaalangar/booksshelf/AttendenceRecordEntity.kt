package com.tahaalangar.booksshelf

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "attendance_records")
data class AttendanceRecord(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val date: String,
    val dayOfWeek: String,
    val hours: Int,
    val minutes: Int,
    val seconds: Int
)

@Entity(tableName = "attendance")
data class Attendance(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val hoursWorked: Double
)
