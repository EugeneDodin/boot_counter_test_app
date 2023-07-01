package com.dodin.auratechassignment.data.repository

import com.dodin.auratechassignment.data.AppPreferences
import com.dodin.auratechassignment.data.db.RebootDao
import com.dodin.auratechassignment.data.db.RebootEventEntity

class RebootEventsRepository(
    private val rebootDao: RebootDao,
    private val appPref: AppPreferences,
) {

    suspend fun getRebootEvents(): List<RebootEventEntity> {
        return rebootDao.findEvents(appPref.lastNotificationTimestamp)
    }

    suspend fun addEvent() {
        rebootDao.insert(RebootEventEntity(timestamp = System.currentTimeMillis()))
    }

    fun updateLastNotificationTimestamp() {
        appPref.lastNotificationTimestamp = System.currentTimeMillis()
    }
}
