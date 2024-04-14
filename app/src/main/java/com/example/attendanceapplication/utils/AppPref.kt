package com.example.attendanceapplication.utils

import android.content.Context
import android.content.SharedPreferences

object AppPref {
    private const val NAME = "mobile_pref_data_v1"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    // list of app specific preferences
    private val IS_LOGGED_IN_DONE = Pair("is_login_done", false)
    private val PROFILE_NAME = Pair("profile_name", "")
    private val IS_THEME_CHANGED = Pair("theme_changed", false)


    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    /**
     * SharedPreferences extension function, so we won't need to call edit() and apply()
     * ourselves on every SharedPreferences operation.
     */
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }


    /*  var profileName: String?
          get() = preferences.getString(PROFILE_NAME.first, PROFILE_NAME.second)
          set(value) = preferences.edit {
              it.putString(IS_LOGGED_IN_DONE.first, value)
          }*/

    var isThemeChanged: Boolean
        get() = preferences.getBoolean(IS_THEME_CHANGED.first, IS_THEME_CHANGED.second)
        set(value) = preferences.edit {
            it.putBoolean(IS_THEME_CHANGED.first, value)
        }


    var profileName: String
        get() = preferences.getString(PROFILE_NAME.first, PROFILE_NAME.second)!!
        set(value) = preferences.edit {
            it.putString(PROFILE_NAME.first, value)
        }

    var isLoggedIn: Boolean
        get() = preferences.getBoolean(IS_LOGGED_IN_DONE.first, IS_LOGGED_IN_DONE.second)
        set(value) = preferences.edit {
            it.putBoolean(IS_LOGGED_IN_DONE.first, value)
        }

    fun setPrefBool(key: String, value: Boolean) {
        preferences.edit().putBoolean(key, value).apply()
    }

    fun getPrefBool(key: String, defaultValue: Boolean = false): Boolean {
        return preferences.getBoolean(key, defaultValue)
    }

    fun setPrefString(key: String, value: String) {
        preferences.edit().putString(key, value).apply()
    }

    fun getPrefString(key: String, defaultValue: String = ""): String? {
        return preferences.getString(key, defaultValue)
    }

}