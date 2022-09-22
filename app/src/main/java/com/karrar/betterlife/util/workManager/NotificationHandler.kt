package com.karrar.betterlife.util.workManager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.VISIBILITY_PUBLIC
import androidx.core.app.NotificationManagerCompat
import com.karrar.betterlife.R
import com.karrar.betterlife.ui.MainActivity
import com.karrar.betterlife.util.vectorToBitmap

object NotificationHandler {
    private const val CHANNEL_ID = "habits_reminder_channel"
    private const val NOTIFICATION_NAME = "appName"


    fun createReminderNotification(context: Context) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE
            else PendingIntent.FLAG_UPDATE_CURRENT
        )

        createNotificationChannel(context)

        val titleNotification = context.getString(R.string.notification_title)
        val subtitleNotification = context.getString(R.string.notification_subtitle)
        val bitmap = vectorToBitmap(context,R.drawable.ic_schedule_black)


        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setLargeIcon(bitmap)
            .setSmallIcon(R.drawable.ic_schedule_black)
            .setContentTitle(titleNotification)
            .setContentText(subtitleNotification)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setVisibility(VISIBILITY_PUBLIC)

        with(NotificationManagerCompat.from(context)) {
            notify(1, builder.build())
        }
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, NOTIFICATION_NAME, importance)
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}