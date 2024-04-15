package com.example.attendanceapplication.ui.leave

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.attendanceapplication.R
import com.example.attendanceapplication.databinding.ActivityLeaveDetailsBinding
import com.example.attendanceapplication.utils.AppUtils
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class LeaveDetailsActivity : AppCompatActivity() {
    private val binding: ActivityLeaveDetailsBinding by lazy {
        ActivityLeaveDetailsBinding.inflate(layoutInflater)
    }


    private val viewModel: LeaveDetailsViewModel by viewModels()

    val builder = MaterialDatePicker.Builder.datePicker()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding.viewModel = viewModel
        binding.activity = this
        binding.lifecycleOwner = this

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun goBack() {

    }

    fun onReject() {

    }

    fun onAccept() {

    }

    fun openCalender(type: String) {
        if (type.equals("start", true))
            startDate()
        else
            endDate()
    }

    fun startDate() {

        val builder = MaterialDatePicker.Builder.datePicker()
        builder.setTitleText("Select Date")

        // Set date range (optional)
        val constraintsBuilder = CalendarConstraints.Builder()
        val now = Calendar.getInstance()
        val maxDate = now.timeInMillis
        now.add(Calendar.MONTH, -1)
        val minDate = now.timeInMillis
        constraintsBuilder.setStart(minDate)
        constraintsBuilder.setEnd(maxDate)
        builder.setCalendarConstraints(constraintsBuilder.build())

        // Set positive button click listener
        builder.setTheme(R.style.CustomMaterialCalendar)
        builder.setPositiveButtonText("OK")
        builder.setPositiveButtonText("OK")


        val picker = builder.build()

        picker.addOnNegativeButtonClickListener({

            picker.dialog!!.dismiss()
        })


        picker.addOnPositiveButtonClickListener {
            val selectedDateInMillis = picker.selection
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = selectedDateInMillis!!

            viewModel.date.value = AppUtils.formatDate(calendar.time)


        }

        picker.show(supportFragmentManager, picker.toString())


    }


    fun endDate() {
        val builder = MaterialDatePicker.Builder.datePicker()
        builder.setTitleText("Select Date")

        // Set date range (optional)
        val constraintsBuilder = CalendarConstraints.Builder()
        val now = Calendar.getInstance()
        val maxDate = now.timeInMillis
        now.add(Calendar.YEAR, 1)
        val sdf = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
        val minDate = sdf.parse(viewModel.date.value)
        constraintsBuilder.setStart(minDate!!.time)
        constraintsBuilder.setEnd(maxDate)
        builder.setCalendarConstraints(constraintsBuilder.build())

        // Set positive button click listener
        builder.setTheme(R.style.CustomMaterialCalendar)
        builder.setPositiveButtonText("OK")
        builder.setPositiveButtonText("OK")


        val picker = builder.build()

        picker.addOnNegativeButtonClickListener({
            picker.dialog!!.dismiss()
        })


        picker.addOnPositiveButtonClickListener {
            val selectedDateInMillis = picker.selection
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = selectedDateInMillis!!

            viewModel.endDate.value = AppUtils.formatDate(calendar.time)


        }
        picker.show(supportFragmentManager, picker.toString())

    }

}