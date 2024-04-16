package com.example.attendanceapplication.ui.leave

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.attendanceapplication.R
import com.example.attendanceapplication.databinding.ActivityLeaveDetailsBinding
import com.example.attendanceapplication.utils.AppUtils
import com.example.attendanceapplication.utils.AppUtils.showToast
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class LeaveDetailsActivity : AppCompatActivity() {
    private val binding: ActivityLeaveDetailsBinding by lazy {
        ActivityLeaveDetailsBinding.inflate(layoutInflater)
    }


    private val viewModel: LeaveDetailsViewModel by viewModels()


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


        val typesOfLeave = listOf(
            "Sick Leave",
            "Vacation",
            "Maternity Leave",
            "Paternity Leave",
            "Bereavement Leave"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, typesOfLeave)
        binding.autoCompleteTextView.setAdapter(adapter)
        binding.autoCompleteTextView.threshold = 1
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
        else {

            if (viewModel.date.value!!.isNullOrEmpty()) {
                showToast(this, "Please select the start date")
                return
            }
            endDate()
        }
    }

    fun startDate() {

        val builder = MaterialDatePicker.Builder.datePicker()
        builder.setTitleText("Start Date")

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


    private fun endDate() {
        val today = Calendar.getInstance()
        val constraintsBuilder = CalendarConstraints.Builder()
        val minDate = today.timeInMillis
        today.add(Calendar.YEAR, 1)
        val maxDate = today.timeInMillis
        constraintsBuilder.setStart(minDate)
        constraintsBuilder.setEnd(maxDate)

        val yesterday = Calendar.getInstance()
        yesterday.add(Calendar.DAY_OF_MONTH, -1)
        constraintsBuilder.setValidator(DateValidatorPointForward.from(yesterday.timeInMillis))


        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("End Date")
            .setCalendarConstraints(constraintsBuilder.build())
            .setTheme(R.style.CustomMaterialCalendar)
            .setPositiveButtonText("OK")
            .build()


        // Set positive button click listener


        datePicker.addOnNegativeButtonClickListener({
            datePicker.dialog!!.dismiss()
        })


        datePicker.addOnPositiveButtonClickListener {
            val selectedDateInMillis = datePicker.selection
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = selectedDateInMillis!!

            viewModel.endDate.value = AppUtils.formatDate(calendar.time)


        }
        datePicker.show(supportFragmentManager, datePicker.toString())

    }

    fun onApplyLeaveClick() {
        if (viewModel.title.value.isNullOrEmpty()) {
            showToast(this,"Title should not be empty")
            return
        }
        if(binding.autoCompleteTextView.text.isNullOrEmpty()){
            showToast(this,"Leave type should not be empty")
            return
        }
        if(viewModel.phoneNumber.value.isNullOrEmpty()){
            showToast(this,"Phone number should not be empty")
            return
        }
        if(viewModel.date.value.isNullOrEmpty()){
            showToast(this,"Start date should not be empty")
            return
        }
        if(viewModel.endDate.value.isNullOrEmpty()){
            showToast(this,"End date should not be empty")
            return
        }

        if(viewModel.reason.value.isNullOrEmpty()){
            showToast(this,"Reason should not be empty")
            return
        }
        showToast(this,"Leave applied successfully")



    }
}