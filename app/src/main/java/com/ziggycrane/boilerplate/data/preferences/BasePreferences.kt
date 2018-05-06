package com.ziggycrane.blueorange.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.ziggycrane.blueorange.di.AppContext
import com.ziggycrane.blueorange.di.qualifiers.PreferencesBase
import javax.inject.Inject

class BasePreferences @Inject constructor(@AppContext context: Context, @PreferencesBase prefBaseName: String, private var gson: Gson) {
    private val PREF_KEY_LOCALE = "PREF_KEY_LOCALE"
    private val PREF_KEY_CSDD_NUMBER = "PREF_KEY_CSDD_NUMBER"

    private lateinit var prefs: SharedPreferences

    init {
        prefs = context.getSharedPreferences(prefBaseName, Context.MODE_PRIVATE)
    }

    fun getLocale(): String {
        // To fix error for first request
        return prefs.getString(PREF_KEY_LOCALE, "ru")
    }


    fun setLocale(locale: String) {
        prefs.edit().putString(PREF_KEY_LOCALE, locale).apply()
    }


    fun getCsddNumber(): String? {
        // To fix error for first request
        return prefs.getString(PREF_KEY_CSDD_NUMBER, null)
    }


    fun setCsddNumber(csddNumber: String) {
        prefs.edit().putString(PREF_KEY_CSDD_NUMBER, csddNumber).apply()
    }
}