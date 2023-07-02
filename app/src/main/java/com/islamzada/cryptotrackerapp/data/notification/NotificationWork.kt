package com.islamzada.cryptotrackerapp.data.notification

import android.content.Context
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Operation
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object NotificationWork {

    // Define constraints for the background work.
    private val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    // Define a OneTimeWorkRequest for the background work.
    private val request = OneTimeWorkRequestBuilder<NotificationWorker>()
        .setConstraints(constraints)
        .build()

    // Activate the WorkManager to perform the background work.
    // This method takes a context, title, and description as parameters.
    // It sets the title and description in the NotificationWorker.
    // It cancels any existing work and enqueues the new work request.
    fun activateWorkManager(context: Context, title: String, description: String): Operation {
        NotificationWorker.title = title
        NotificationWorker.description = description

        WorkManager.getInstance(context).cancelAllWork()

        return WorkManager.getInstance(context).enqueue(request)
    }
}