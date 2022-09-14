package com.karrar.betterlife.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import com.karrar.betterlife.R
import com.karrar.betterlife.ui.workManager.ReminderNotificationWorker
import java.util.*
import java.util.concurrent.TimeUnit
import javax.xml.datatype.DatatypeConstants.DAYS


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val calendar = Calendar.getInstance()

        calendar[Calendar.HOUR_OF_DAY] = 23
        calendar[Calendar.MINUTE] = 43
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
//        val notificationWork = OneTimeWorkRequest.Builder(ReminderNotificationWorker::class.java)
//            .setInitialDelay(delay, TimeUnit.MILLISECONDS).setInputData(data).build()
//
//        val instanceWorkManager = WorkManager.getInstance(this)
//        instanceWorkManager.beginUniqueWork("appName_notification_work",
//            ExistingWorkPolicy.REPLACE, notificationWork).enqueue()

        val notificationWork =
//            PeriodicWorkRequest.Builder(ReminderNotificationWorker::class.java, 1,TimeUnit.DAYS)
                 PeriodicWorkRequestBuilder<ReminderNotificationWorker>(1, TimeUnit.DAYS)
                .setInitialDelay(delay,TimeUnit.MILLISECONDS)
                .setInputData(data)
                .build()
        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork("appName_notification_work",
                ExistingPeriodicWorkPolicy.REPLACE, notificationWork)
    }
}