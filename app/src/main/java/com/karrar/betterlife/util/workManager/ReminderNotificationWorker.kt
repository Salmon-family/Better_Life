package com.karrar.betterlife.util.workManager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.karrar.betterlife.data.repository.BetterRepository
import java.util.*

class ReminderNotificationWorker(
    private val appContext: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(appContext, workerParameters) {
    override suspend fun doWork(): Result {
        val date = Date()
        val result = BetterRepository().isAnyHabitsInThisDay(date.time)
        if (result.isNullOrEmpty())
            NotificationHandler.createReminderNotification(appContext)

        return Result.success()
    }
}
