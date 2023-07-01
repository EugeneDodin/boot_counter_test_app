package com.dodin.auratechassignment.presentation.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.dodin.auratechassignment.Dependency
import com.dodin.auratechassignment.usecase.UpdateDbUseCase

class UpdateDBWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    private val updateDb: UpdateDbUseCase by lazy {
        UpdateDbUseCase(Dependency.repository)
    }

    override suspend fun doWork(): Result {
        updateDb()
        return Result.success()
    }

    companion object {
        fun scheduleWorker(context: Context) {
            val request =
                OneTimeWorkRequestBuilder<UpdateDBWorker>()
                    .build()
            WorkManager.getInstance(context).enqueue(request)
        }
    }
}