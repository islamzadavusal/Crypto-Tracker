package com.islamzada.cryptotrackerapp.data.notification

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import javax.inject.Inject

class NotificationWorker @Inject constructor(
    val context: Context,
    workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {

    companion object {
        var title = "" // A static variable to store the notification title.
        var description = "" // A static variable to store the notification description.
    }

    // Override the doWork() method from the CoroutineWorker class.
    // This method contains the background work to be performed.
    override suspend fun doWork(): Result {
        if (title.isNotEmpty() && description.isNotEmpty()) {
            NotificationSender.sendNotification(context, title, description)
        } else {
            NotificationSender.sendNotification(
                context,
                "Price alert",
                "Enter app and check currencies' price limitation"
            )
        }
        return Result.success() // Return Result.success() to indicate successful completion of the work.
    }
}