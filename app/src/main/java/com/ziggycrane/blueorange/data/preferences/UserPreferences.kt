package com.ziggycrane.blueorange.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.ziggycrane.blueorange.di.AppContext
import com.ziggycrane.blueorange.di.qualifiers.PreferencesUser
import javax.inject.Inject

class UserPreferences {
    private val PREF_KEY_USER_LOGGED_IN = "PREF_KEY_USER_LOGGED_IN"
    private val PREF_KEY_AUTH_KEY = "PREF_KEY_AUTH_KEY"

    private lateinit var prefs: SharedPreferences

    private lateinit var gson: Gson

    @Inject
    constructor(@AppContext context: Context, @PreferencesUser prefUserName: String, gson: Gson) {
        prefs = context.getSharedPreferences(prefUserName, Context.MODE_PRIVATE)
        this.gson = gson
    }


    fun getLoggedIn(): Boolean? {
        return prefs.getBoolean(PREF_KEY_USER_LOGGED_IN, false)
    }


    fun setLoggedIn(loggedIn: Boolean?) {
        prefs.edit().putBoolean(PREF_KEY_USER_LOGGED_IN, loggedIn!!).apply()
    }


    fun getAuthKey(): String? {
        return prefs.getString(PREF_KEY_AUTH_KEY, null)
    }


    fun setAuthKey(accessToken: String) {
        prefs.edit().putString(PREF_KEY_AUTH_KEY, accessToken).apply()
    }

}