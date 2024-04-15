package com.example.attendanceapplication.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object AppUtils {

    fun formatDate(date: Date): String {
        val dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
        return dateFormat.format(date)
    }


}