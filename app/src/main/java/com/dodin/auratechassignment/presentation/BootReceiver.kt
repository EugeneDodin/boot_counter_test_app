package com.dodin.auratechassignment.presentation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.dodin.auratechassignment.presentation.workers.NotificationWorker

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        NotificationWorker.scheduleWorker(context)
    }
}
