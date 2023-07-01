package com.dodin.auratechassignment.presentation.workers

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.dodin.auratechassignment.R
import java.util.concurrent.TimeUnit

class NotificationWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    override fun doWork(): Result {
        createNotificationChannel()
        createNotification()

        return Result.success()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val name = "Boot Notification"
            val descriptionText = "Boot Notifications Channel"
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager =
                applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun createNotification(): Notification {
        val builder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("123")
            .setContentText("321")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        return builder.build()
    }

    companion object {
        private const val CHANNEL_ID = "boot-notifications"
        private const val WORKER_INTERVAL_MIN = 15L

        fun scheduleWorker(context: Context) {
            val request =
                PeriodicWorkRequestBuilder<NotificationWorker>(WORKER_INTERVAL_MIN, TimeUnit.MINUTES)
                    .build()
            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                "boot-notifications",
                ExistingPeriodicWorkPolicy.KEEP,
                request
            )
        }
    }
}
