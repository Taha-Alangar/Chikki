package com.tahaalangar.paniwala
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TimeSlotDialogFragment : DialogFragment() {

    interface OnTimeSlotSelectedListener {
        fun onTimeSlotSelected(timeSlot: String)
    }

    private var listener: OnTimeSlotSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = targetFragment as OnTimeSlotSelectedListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$targetFragment must implement OnTimeSlotSelectedListener")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val timeSlots = generateTimeSlots()
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Select Delivery Time Slot")
            .setItems(timeSlots) { _, which ->
                listener?.onTimeSlotSelected(timeSlots[which])
            }
        return builder.create()
    }

    private fun generateTimeSlots(): Array<String> {
        val timeSlots = mutableListOf<String>()
        val dateFormat = SimpleDateFormat("EEEE", Locale.getDefault())
        val calendar = Calendar.getInstance()

        for (i in 0..2) {
            val day = dateFormat.format(calendar.time)
            timeSlots.add("$day 10:00am to 12:00pm")
            timeSlots.add("$day 2:00pm to 4:00pm")
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }
        return timeSlots.toTypedArray()
    }
}