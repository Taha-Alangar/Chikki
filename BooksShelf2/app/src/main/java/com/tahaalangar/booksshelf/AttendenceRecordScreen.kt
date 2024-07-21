package com.tahaalangar.booksshelf

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.math.roundToInt

class AttendenceRecordScreen : AppCompatActivity() {

    private lateinit var calendarRecyclerView: RecyclerView
    private lateinit var monthYearTextView: TextView
    private lateinit var attendanceRecordDao: AttendanceRecordDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendence_record_screen)

        calendarRecyclerView = findViewById(R.id.calendarRecyclerView)
        monthYearTextView = findViewById(R.id.monthYearTextView)

        val db = AppDatabase.getDatabase(applicationContext)
        attendanceRecordDao = db.attendanceRecordDao()

        displayAttendance()

    }
    private fun displayAttendance() {
        // Get current month and year
        val calendar = Calendar.getInstance()
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)
        val monthName = SimpleDateFormat("MMMM", Locale.getDefault()).format(calendar.time)
        monthYearTextView.text = "$monthName $year"

        // Get the first and last day of the current month
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val startDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        val endDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)

        Log.d("QueryDates", "startDate: $startDate, endDate: $endDate")

        lifecycleScope.launch {
            val attendanceList = attendanceRecordDao.getAttendanceForMonth(startDate, endDate)

            // Log the attendance records retrieved
            Log.d("AttendanceList", attendanceList.toString())

            val dateFormat = SimpleDateFormat("dd MMM EEE", Locale.getDefault())
            val attendanceMap = attendanceList.associate {
                dateFormat.format(SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(it.date)!!) to getTimeStringFromDouble(
                    it.hours.toDouble() + it.minutes.toDouble() / 60 + it.seconds.toDouble() / 3600
                )
            }

            // Log the attendance map
            Log.d("AttendanceMap", attendanceMap.toString())

            val daysInMonth = getDaysInMonth(calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR))

            // Log the days in the month
            Log.d("DaysInMonth", daysInMonth.toString())

            setupRecyclerView(daysInMonth, attendanceMap)
        }
    }




    private fun setupRecyclerView(days: List<String>, attendanceMap: Map<String, String>) {
        val adapter = CalendarAdapter(days, attendanceMap)
        calendarRecyclerView.layoutManager = GridLayoutManager(this, 3)
        calendarRecyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun getDaysInMonth(month: Int, year: Int): List<String> {
        val days = mutableListOf<String>()
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.DAY_OF_MONTH, 1)

        val dateFormat = SimpleDateFormat("dd MMM EEE", Locale.getDefault())
        while (calendar.get(Calendar.MONTH) == month) {
            days.add(dateFormat.format(calendar.time))
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        return days
    }



    private fun getTimeStringFromDouble(time: Double): String {
        val totalSeconds = (time * 3600).toInt()
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60

        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }

}