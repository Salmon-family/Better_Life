package com.karrar.betterlife.ui.workManager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class ReminderNotificationWorker(private val appContext: Context, workerParameters: WorkerParameters) : Worker(appContext, workerParameters) {
    override fun doWork(): Result {
        NotificationHandler.createReminderNotification(appContext)
        return Result.success()
    }
}
