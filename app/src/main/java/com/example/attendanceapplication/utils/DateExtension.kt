package com.example.attendanceapplication.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by $.
 * Date: $
 * Time: $
 */
fun Date.toStringFormat(stringFormat: String = "dd-MM-yyyy"): String {
    val dateFormat = SimpleDateFormat(stringFormat, Locale.getDefault())
    return try {
        dateFormat.format(this)
    } catch (e: ParseException) {
        e.printStackTrace()
        "N/A"
    }
}

fun String.toDate(stringFormat: String = "dd-MM-yyyy"): Date? {
    val format = SimpleDateFormat(stringFormat, Locale.US)
    return try {
        format.parse(this)
    } catch (e: ParseException) {
        e.printStackTrace()
        null
    }
}


fun getFormatDate(day: Int, month: Int, year: Int): String {
    val twoDigit = "%1$02d" // two digits
    val twoDigitDay = String.format(twoDigit, day)
    val twoDigitMonth = String.format(twoDigit, month)
    return "${twoDigitDay}-${twoDigitMonth}-${year}"
}