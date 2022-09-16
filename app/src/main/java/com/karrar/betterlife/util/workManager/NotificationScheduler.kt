package com.karrar.betterlife.util.workManager

import android.content.Context
import androidx.work.Data
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.*
import java.util.concurrent.TimeUnit

class NotificationScheduler (private val context: Context){

    fun initialDelay(){

        val calendar = Calendar.getInstance()
        calendar[Calendar.HOUR_OF_DAY] = 22
        calendar[Calendar.MINUTE] = 0
        calendar[Calendar.SECOND] = 0
        val customTime = calendar.timeInMillis
        val currentTime = System.currentTimeMillis()
        if (customTime > currentTime) {
            val data = Data.Builder().putInt("appName_notification_id", 0).build()
            val delay = customTime - currentTime
            scheduleNotification(delay, data)
    }
}
    private fun scheduleNotification(delay: Long, data: Data) {
        val notificationWork =
            PeriodicWorkRequestBuilder<ReminderNotificationWorker>(24, TimeUnit.HOURS)
                .setInitialDelay(delay, TimeUnit.MILLISECONDS)
                .setInputData(data)
                .build()
        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork("appName_notification_work",
                ExistingPeriodicWorkPolicy.REPLACE, notificationWork)
    }
}
