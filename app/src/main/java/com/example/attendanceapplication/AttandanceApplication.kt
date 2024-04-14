package com.example.attendanceapplication

import android.app.Application
import com.example.attendanceapplication.utils.AppPref
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class AttandanceApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this@AttandanceApplication
        AppPref.init(this)
    }

    companion object {
        var appInstance: AttandanceApplication? = null
        fun getInstance(): AttandanceApplication? {
            return appInstance
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        appInstance = null
    }
}