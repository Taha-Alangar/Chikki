package com.tahaalangar.booksshelf

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AttendanceRecord::class, Attendance::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun attendanceRecordDao(): AttendanceRecordDao
    abstract fun attendanceDao(): AttendanceDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "attendance_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
