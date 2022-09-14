package com.karrar.betterlife.ui.workManager

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

object NotificationHandler {
    private const val CHANNEL_ID = "transactions_reminder_channel"

    fun createReminderNotification(context: Context) {
        //  No back-stack when launched
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(context, 0, intent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE
            else PendingIntent.FLAG_UPDATE_CURRENT)

        createNotificationChannel(context) // This won't create a new channel everytime, safe to call

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle("Remember to add evaluation to your activity!")
            .setContentText("I would like to remind you about your daily habit.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent) // For launching the MainActivity
            .setAutoCancel(true) // Remove notification when tapped
            .setVisibility(VISIBILITY_PUBLIC) // Show on lock screen

        with(NotificationManagerCompat.from(context)) {
            notify(1, builder.build())
        }
    }

    /**
     * Required on Android O+
     */
    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Daily Reminders"
            val descriptionText = "This channel sends daily reminders to add your transactions"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}