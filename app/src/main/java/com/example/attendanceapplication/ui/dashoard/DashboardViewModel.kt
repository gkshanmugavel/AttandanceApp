package com.example.attendanceapplication.ui.dashoard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import androidx.lifecycle.*
import androidx.paging.*
import com.example.attendanceapplication.adapter.DateSelectionPageSource
import com.example.attendanceapplication.utils.DateDetailsUI
import com.example.attendanceapplication.utils.toDateDetails
import com.example.attendanceapplication.utils.toStringFormat
import java.util.*

class DashboardViewModel : ViewModel() {

    private val _selectedDate = MutableLiveData(Date().toStringFormat())
    val selectedDate: LiveData<String> get() = _selectedDate

    private val dateSelectionPager = _selectedDate.switchMap { changedSelectedDate ->
        Pager(PagingConfig(pageSize = 10)) {
            DateSelectionPageSource(changedSelectedDate)
        }.liveData.cachedIn(viewModelScope)
    }

    val dateDetailsList: LiveData<PagingData<DateDetailsUI>> =
        dateSelectionPager.map { pagingData ->
            pagingData.map { mealDate ->
                mealDate.toDateDetails()
            }
        }

    private val _resetDateList = MutableLiveData<Long>()
    val resetDateList: LiveData<Long> get() = _resetDateList

    @JvmOverloads
    fun updateCurrentSelectedDate(date: String, invalidateList: Boolean = false) {
        if (invalidateList) {
            _resetDateList.value = System.currentTimeMillis()
        }
        _selectedDate.value = date
    }
}