package com.example.attendanceapplication.utils

import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object AppUtils {

    fun formatDate(date: Date): String {
        val dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
        return dateFormat.format(date)
    }


    fun showToast(context: Context, message: String) {

        Toast.makeText(context, "${message}", Toast.LENGTH_LONG).show()
    }
}