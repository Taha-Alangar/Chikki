package com.tahaalangar.booksshelf

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Locale

// CalendarAdapter.kt
class CalendarAdapter(
    private val days: List<String>,
    private val attendanceMap: Map<String, String>
) : RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.calendar_day_item, parent, false)
        return CalendarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val day = days[position]
        val time = attendanceMap[day] ?: "00:00:00" // Provide a default time if not found
        holder.bind(day, time)
    }

    override fun getItemCount(): Int = days.size

    class CalendarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dayTextView: TextView = itemView.findViewById(R.id.dayTextView)
        private val hoursTextView: TextView = itemView.findViewById(R.id.hoursTextView)

        fun bind(day: String, time: String) {
            dayTextView.text = day
            hoursTextView.text = time
        }
    }
}
