package com.ziggycrane.boilerplate.utils

import android.Manifest
import android.provider.ContactsContract.Directory.PACKAGE_NAME

object Constants {
    private const val PREF_NAME = "$PACKAGE_NAME.PREFS"
    const val PREF_BASE = "$PREF_NAME.BASE"
    const val PREF_USER = "$PREF_NAME.USER"

    var PERMISSIONS = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
}
