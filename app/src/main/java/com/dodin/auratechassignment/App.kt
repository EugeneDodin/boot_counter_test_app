package com.dodin.auratechassignment

import android.app.Application

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Dependency.appContext = this
    }
}
