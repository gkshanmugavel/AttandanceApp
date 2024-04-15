package com.example.attendanceapplication

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.attendanceapplication.adapter.DateSelectionAdapter
import com.example.attendanceapplication.databinding.ActivityDashboardBinding
import com.example.attendanceapplication.utils.DateDetailsUI
import com.example.attendanceapplication.utils.getFormatDate
import com.example.attendanceapplication.utils.maxDate
import com.example.attendanceapplication.utils.minDate
import com.example.attendanceapplication.utils.toDate
import com.example.attendanceapplication.utils.toDateDetails
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

//https://android-arsenal.com/details/1/7061
@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    private val binding: ActivityDashboardBinding by lazy {
        ActivityDashboardBinding.inflate(layoutInflater)
    }

    private val mainViewModel: DashboardViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.activity = this
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dateSelectionAdapter = DateSelectionAdapter { dateDetailsUI ->
            setSelectedDate(dateDetailsUI)
        }

      /*  binding.btnPickDate.setOnClickListener {
            selectDateFromDatePickerDialog(mainViewModel.selectedDate.value!!)
        }*/

        val rvDates = binding.viewCalnderView
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(rvDates)
        rvDates.adapter = dateSelectionAdapter

        mainViewModel.dateDetailsList.observe(this) { integerPagingData ->
            dateSelectionAdapter.submitData(lifecycle, integerPagingData)
        }

        mainViewModel.selectedDate.observe(this) { selectedDate ->
            val dateDetailsUI = selectedDate.toDate()?.toDateDetails()
            dateDetailsUI?.let {
                setSelectedDate(it)
            }
        }

        mainViewModel.resetDateList.observe(this) { timeInMillis ->
            if (timeInMillis != null) {
                dateSelectionAdapter.submitData(lifecycle, PagingData.empty())

            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setSelectedDate(dateDetailsUI: DateDetailsUI) {
       /* binding.txtSelectedDate.text = """
                    ${dateDetailsUI.dayOfWeek}
                    ${dateDetailsUI.day} ${dateDetailsUI.monthName}
                    ${dateDetailsUI.year}
                """.trimIndent()*/
    }

    private fun selectDateFromDatePickerDialog(currentSelectedDate: String) {
        val cal = Calendar.getInstance()
        cal.time = currentSelectedDate.toDate()!!
        val day = cal[Calendar.DAY_OF_MONTH]
        val month = cal[Calendar.MONTH]
        val year = cal[Calendar.YEAR]

        val listener =
            DatePickerDialog.OnDateSetListener { _, selectedYear: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate = getFormatDate(dayOfMonth, monthOfYear + 1, selectedYear)
                mainViewModel.updateCurrentSelectedDate(selectedDate, true)
            }
        val dpDialog = DatePickerDialog(this, listener, year, month, day)
        dpDialog.datePicker.maxDate = maxDate.time
        dpDialog.datePicker.minDate = minDate.time
        dpDialog.show()
    }

}