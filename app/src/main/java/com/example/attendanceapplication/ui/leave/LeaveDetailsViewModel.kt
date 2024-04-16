package com.example.attendanceapplication.ui.leave

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LeaveDetailsViewModel @Inject constructor() : ViewModel() {
    var date = MutableLiveData("")
    var endDate = MutableLiveData("")
    var title = MutableLiveData("")
    var phoneNumber = MutableLiveData("")
    var reason = MutableLiveData("")
}