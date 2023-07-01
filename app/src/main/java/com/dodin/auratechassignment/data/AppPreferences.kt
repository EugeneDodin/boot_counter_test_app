package com.dodin.auratechassignment.data

import android.content.Context
import androidx.core.content.edit

class AppPreferences(context: Context) {
    private val sharedPrefs = context.getSharedPreferences("app-pref", Context.MODE_PRIVATE)

    var lastNotificationTimestamp: Long
        set(value) {
            sharedPrefs.edit {
                putLong(LAST_NOTIFICATION_TIMESTAMP, value)
            }
        }
        get() = sharedPrefs.getLong(LAST_NOTIFICATION_TIMESTAMP, 0)

    private companion object {
        const val LAST_NOTIFICATION_TIMESTAMP = "last_notification_timestamp"
    }
}
