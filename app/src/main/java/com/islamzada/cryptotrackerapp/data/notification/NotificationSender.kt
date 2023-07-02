package com.islamzada.cryptotrackerapp.data.notification

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.islamzada.cryptotrackerapp.R
import com.islamzada.cryptotrackerapp.message.Resource
import com.islamzada.cryptotrackerapp.ui.activity.MainActivity

object NotificationSender {
    fun sendNotification(context: Context, title: String, desc: String): Resource<String> {

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }

        // Check if the Android version is Oreo (API level 26) or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "PRICE_ALERT"
            val channelName = "Price Alert Channel"
            val channelDescription = "Channel for Price Alerts"
            val importance = NotificationManager.IMPORTANCE_HIGH

            // Create a notification channel for Oreo and higher versions
            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = channelDescription
            }

            // Get the system's notification manager
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            // Create the notification channel
            notificationManager.createNotificationChannel(channel)

            // Create a pending intent to open the MainActivity when the notification is clicked
            val pending =
                PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

            // Build the notification using the NotificationCompat builder
            val notification = NotificationCompat.Builder(context, "PRICE_ALERT")
                .setContentTitle(title)
                .setContentText(desc)
                .setSmallIcon(R.mipmap.app_icon)
                .setAutoCancel(true)
                .setContentIntent(pending)

            // Send the notification using the NotificationManagerCompat
            with(NotificationManagerCompat.from(context)) {
                // Check if the app has the required permission to post notifications
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return Resource.error("Permission not granted", null)
                }
                // Notify with notification ID 1
                notify(1, notification.build())
            }
        }

        // Return a success Resource object with an empty string
        return Resource.success("")
    }
}