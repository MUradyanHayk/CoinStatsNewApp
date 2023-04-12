package com.example.coinstatenewapp.utils

import android.app.Activity
import android.content.Context

object PrefsManager {
    fun putFavorite(activity: Activity, key: String, value: Boolean) {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE) ?: return
        sharedPref.edit().putBoolean(key, value).apply()
    }

    fun getFavorite(activity: Activity, key: String): Boolean {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE) ?: return false
        return sharedPref.getBoolean(key, false)
    }
}