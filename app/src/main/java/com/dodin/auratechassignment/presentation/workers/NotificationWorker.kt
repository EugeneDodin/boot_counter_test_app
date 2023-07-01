package com.dodin.auratechassignment.presentation.workers

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.dodin.auratechassignment.Dependency
import com.dodin.auratechassignment.R
import com.dodin.auratechassignment.usecase.GetNotificationTextUseCase
import java.util.concurrent.TimeUnit

class NotificationWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    private val getNotificationTextUseCase: GetNotificationTextUseCase by lazy {
        GetNotificationTextUseCase(Dependency.repository)
    }

    override suspend fun doWork(): Result {
        createNotificationChannel()
        val text = getNotificationTextUseCase()
        createNotification(text)

        return Result.success()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance).apply {
                description = CHANNEL_DESCRIPTION
            }
            // Register the channel with the system
            val notificationManager =
                applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun createNotification(text: String): Notification {
        val builder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Reboot events since last notification")
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        return builder.build()
    }

    companion object {
        private const val CHANNEL_ID = "boot-notifications"
        private const val CHANNEL_NAME = "Boot Notification"
        private const val CHANNEL_DESCRIPTION = "Boot Notifications Channel"
        private const val WORKER_INTERVAL_MIN = 15L

        fun scheduleWorker(context: Context) {
            val request =
                PeriodicWorkRequestBuilder<UpdateDBWorker>(WORKER_INTERVAL_MIN, TimeUnit.MINUTES)
                    .build()
            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                "boot-notifications",
                ExistingPeriodicWorkPolicy.KEEP,
                request
            )
        }
    }
}
