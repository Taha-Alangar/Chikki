package com.tahaalangar.booksshelf

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class AttendenceScreen : AppCompatActivity() {

    private var timerStarted = mutableMapOf<String, Boolean>()
    private lateinit var serviceIntent: Intent
    private var times = mutableMapOf<String, Double>()
    private val timerIds = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday","Saturday")
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var totalTimeTV:TextView
    private lateinit var remainingTimeTv:TextView
    private lateinit var dateTv: TextView // Added

    private lateinit var attendeceRecordBtn: Button // Added

    private lateinit var attendanceRecordDao: AttendanceRecordDao
    private lateinit var attendanceDao: AttendanceDao

    private val totalHours = 54
    private val totalMillis = totalHours * 60 * 60 * 1000L
    private var remainingMillis = totalMillis

    @SuppressLint("DiscouragedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendence_screen)

        // Initialize Room database and DAOs
        val db = AppDatabase.getDatabase(applicationContext)
        attendanceRecordDao = db.attendanceRecordDao()
        attendanceDao = db.attendanceDao()


        totalTimeTV=findViewById(R.id.totalTimeTV)
        remainingTimeTv=findViewById(R.id.remainingTimeTV)
        dateTv=findViewById(R.id.dateTv) // Initialize dateTv


        attendeceRecordBtn=findViewById(R.id.attendenceRecordBtn) // Initialize dateTv

        attendeceRecordBtn.setOnClickListener {
            startActivity(Intent(this,AttendenceRecordScreen::class.java))
        }
        // Get the current day of the week (e.g., Calendar.MONDAY, Calendar.TUESDAY, etc.)
        val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)

        // Hide all the "Punch In" buttons
        hideAllPunchInButtons()

        // Show the "Punch In" button for the current day
        when (currentDay) {
            Calendar.MONDAY -> showPunchInButton("Monday")
            Calendar.TUESDAY -> showPunchInButton("Tuesday")
            Calendar.WEDNESDAY -> showPunchInButton("Wednesday")
            Calendar.THURSDAY -> showPunchInButton("Thursday")
            Calendar.FRIDAY -> showPunchInButton("Friday")
            Calendar.SATURDAY -> showPunchInButton("Saturday")
        }

        // Set today's date
        val currentWeekRange = getCurrentWeekRange()
        dateTv.text = currentWeekRange

        sharedPreferences = getSharedPreferences("TimerPrefs", Context.MODE_PRIVATE)

        timerIds.forEach { timerId ->
            timerStarted[timerId] = sharedPreferences.getBoolean("${timerId}_started", false)
            times[timerId] = sharedPreferences.getFloat("${timerId}_time", 0.0f).toDouble()
            val startStopButtonId = resources.getIdentifier("startStopButton_$timerId", "id", packageName)
            findViewById<Button>(startStopButtonId).setOnClickListener {
                startStopTimer(timerId)
            }
        }
        serviceIntent = Intent(applicationContext, TimerService::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATED),
                    RECEIVER_EXPORTED
                )
            }
        }
        // Load remaining time from SharedPreferences
        remainingMillis = sharedPreferences.getLong("remainingMillis", totalMillis)
        updateUI()
    }

    @SuppressLint("DiscouragedApi")
    private fun hideAllPunchInButtons() {
        timerIds.forEach { timerId ->
            val startStopButtonId = resources.getIdentifier("startStopButton_$timerId", "id", packageName)
            findViewById<Button>(startStopButtonId).visibility = View.GONE
        }
    }
    @SuppressLint("DiscouragedApi")
    private fun showPunchInButton(day: String) {
        val startStopButtonId = resources.getIdentifier("startStopButton_$day", "id", packageName)
        findViewById<Button>(startStopButtonId).visibility = View.VISIBLE
    }
    // Function to get current date
    private fun getCurrentWeekRange(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

        // Set calendar to the start of the current day
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        // Find the most recent Sunday
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            calendar.add(Calendar.DAY_OF_MONTH, -1)

        }
        val startDate = calendar.time

        // Find the upcoming Saturday
        calendar.add(Calendar.DAY_OF_MONTH, 6)
        val endDate = calendar.time

        return "${dateFormat.format(startDate)} to ${dateFormat.format(endDate)}"
    }
    private fun startStopTimer(timerId: String) {
        if (timerStarted[timerId] == true) {
            stopTimer(timerId)
        } else {
            startTimer(timerId)
        }
    }
    private fun startTimer(timerId: String) {
        serviceIntent.putExtra(TimerService.TIME_EXTRA, times[timerId])
        serviceIntent.putExtra(TimerService.TIMER_ID, timerId)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent)
        } else {
            startService(serviceIntent)
        }
        timerStarted[timerId] = true
        updateButton(timerId)
        saveTimerState(timerId)
    }
    private fun stopTimer(timerId: String) {
        stopService(serviceIntent)
        timerStarted[timerId] = false
        updateButton(timerId)
        saveTimerState(timerId)
        saveAttendance(timerId)
    }

    private val updateTime: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val timerId = intent.getStringExtra(TimerService.TIMER_ID) ?: return
            times[timerId] = intent.getDoubleExtra(TimerService.TIME_EXTRA, 0.0)
            saveTimerState(timerId)
            updateUI()
        }
    }
    private fun updateUI() {
        var totalTime = 0.0
        timerIds.forEach { timerId ->
            val timeTVId = resources.getIdentifier("timeTV_$timerId", "id", packageName)
            findViewById<TextView>(timeTVId).text = getTimeStringFromDouble(times[timerId]!!)
            totalTime += times[timerId]!!
            updateButton(timerId)
        }
        totalTimeTV.text = getTimeStringFromDouble(totalTime)
        // Update remaining time
        val remainingTime = totalMillis - totalTime * 1000
        remainingMillis = remainingTime.roundToLong()
        remainingTimeTv.text = getTimeStringFromMillis(remainingMillis)
    }

    private fun updateButton(timerId: String) {
        val startStopButtonId = resources.getIdentifier("startStopButton_$timerId", "id", packageName)
        val startStopButton = findViewById<Button>(startStopButtonId)
        startStopButton.text = if (timerStarted[timerId] == true) "Punch Out" else "Punch In"
    }

    private fun getTimeStringFromDouble(time: Double): String {
        val resultInt = time.roundToInt()
        val hours = resultInt % 86400 / 3600
        val minutes = resultInt % 86400 % 3600 / 60
        val seconds = resultInt % 86400 % 3600 % 60

        return makeTimeString(hours, minutes, seconds)
    }
    private fun getTimeStringFromMillis(millis: Long): String {
        val hours = millis / 3600000
        val minutes = (millis % 3600000) / 60000
        val seconds = (millis % 60000) / 1000

        return makeTimeString(hours.toInt(), minutes.toInt(), seconds.toInt())
    }

    private fun makeTimeString(hour: Int, min: Int, sec: Int): String =
        String.format("%02d:%02d:%02d", hour, min, sec)

    private fun saveTimerState(timerId: String) {
        with(sharedPreferences.edit()) {
            putBoolean("${timerId}_started", timerStarted[timerId] == true)
            putFloat("${timerId}_time", times[timerId]!!.toFloat())
            putLong("remainingMillis", remainingMillis)
            apply()
        }
    }
    private fun saveAttendance(timerId: String) {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = dateFormat.format(calendar.time)
        val dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())
        val totalSeconds = times[timerId]?.roundToInt() ?: 0
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60

        val attendanceRecord = AttendanceRecord(
            date = date,
            dayOfWeek = dayOfWeek,
            hours = hours,
            minutes = minutes,
            seconds = seconds
        )

        Log.d("SaveAttendance", "Inserting record: $attendanceRecord")

        CoroutineScope(Dispatchers.IO).launch {
            attendanceRecordDao.insert(attendanceRecord)
            attendanceDao.insert(Attendance(date = date, hoursWorked = times[timerId]!!))
        }
    }



}
