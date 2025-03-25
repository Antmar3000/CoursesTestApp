package com.am.coursestest.data

import android.content.SharedPreferences

class IsFirstLaunched(private val sharedPrefs: SharedPreferences) {
    fun isFirstLaunched(): Boolean {
        return sharedPrefs.getBoolean("is_first", true)
    }

    fun setLaunched() {
        sharedPrefs.edit().putBoolean("is_first", false).apply()
    }
}
