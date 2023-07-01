package com.dodin.auratechassignment

import android.content.Context
import androidx.room.Room
import com.dodin.auratechassignment.data.AppPreferences
import com.dodin.auratechassignment.data.db.Database
import com.dodin.auratechassignment.data.repository.RebootEventsRepository

object Dependency {
    lateinit var appContext: Context

    val repository: RebootEventsRepository by lazy {
        RebootEventsRepository(db.rebootDao(), appPref)
    }

    val appPref: AppPreferences by lazy {
        AppPreferences(appContext)
    }

    private val db: Database by lazy {
        Room.databaseBuilder(
            appContext,
            Database::class.java, "reboot-database"
        ).build()
    }
}