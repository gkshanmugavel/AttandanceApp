package com.example.attendanceapplication

import android.app.Application
import com.example.attendanceapplication.utils.AppPref
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class AttendanceApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        appInstance = this@AttendanceApplication
        AppPref.init(this)
    }

    companion object {
        var appInstance: AttendanceApplication? = null
        fun getInstance(): AttendanceApplication? {
            return appInstance
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        appInstance = null
    }
}